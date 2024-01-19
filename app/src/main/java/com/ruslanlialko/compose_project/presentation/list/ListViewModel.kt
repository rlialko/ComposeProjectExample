package com.ruslanlialko.compose_project.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruslanlialko.compose_project.domain.Resource
import com.ruslanlialko.compose_project.domain.use_case.GetExampleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getExampleUseCase: GetExampleUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListState())
    val state: StateFlow<ListState> = _state.asStateFlow()

    private var getInitialDataJob: Job? = null

    fun search(query: String) {
        getInitialDataJob?.cancel()
        if (query.isEmpty()) {
            _state.value = ListState()
            return
        }
        if (query.length < 3) {
            _state.value = ListState(isInputValid = false)
            return
        }
        getInitialDataJob = getExampleUseCase(query, 0, 20).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ListState(someValue = result.data?.someValue ?: 0)
                }

                is Resource.Error -> {
                    _state.value = ListState(error = result.error)
                }

                is Resource.Loading -> {
                    _state.value = ListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}