package com.ruslanlialko.compose_project.data.repository

import com.ruslanlialko.compose_project.data.remote.ExampleApi
import com.ruslanlialko.compose_project.data.dto.ExampleDto
import com.ruslanlialko.compose_project.domain.repository.ExampleRepository
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor(private val exampleApi: ExampleApi) : ExampleRepository {

    override suspend fun getExample(query: String, page: Int, perPage: Int): ExampleDto {
        return exampleApi.getExampleData(query, page, perPage)
    }

}