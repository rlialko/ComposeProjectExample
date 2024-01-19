package com.ruslanlialko.compose_project.presentation.list

data class ListState(
    val someValue: Int = 0,
    val isLoading: Boolean = false,
    val isInputValid: Boolean = true,
    val error: Throwable? = null
)