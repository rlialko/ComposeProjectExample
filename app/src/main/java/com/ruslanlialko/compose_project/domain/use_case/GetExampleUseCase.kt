package com.ruslanlialko.compose_project.domain.use_case

import com.ruslanlialko.compose_project.data.dto.toRepos
import com.ruslanlialko.compose_project.domain.Resource
import com.ruslanlialko.compose_project.domain.exception.NoInternetException
import com.ruslanlialko.compose_project.domain.exception.TooManyRequestsException
import com.ruslanlialko.compose_project.domain.model.Example
import com.ruslanlialko.compose_project.domain.repository.ExampleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetExampleUseCase @Inject constructor(private val exampleRepository: ExampleRepository) {

    operator fun invoke(query: String, page: Int, perPage: Int): Flow<Resource<Example>> = flow {
        emit(Resource.Loading())
        try {
            val repos = exampleRepository.getExample(query, page, perPage).toRepos()
            emit(Resource.Success(repos))
        } catch (e: HttpException) {
            if (e.code() == 403) {
                emit(Resource.Error(TooManyRequestsException()))
                return@flow
            }
            emit(Resource.Error(e))
        } catch (e: IOException) {
            emit(Resource.Error(NoInternetException()))
        }
    }
}