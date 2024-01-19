package com.ruslanlialko.compose_project.domain.repository

import com.ruslanlialko.compose_project.data.dto.ExampleDto

interface ExampleRepository {

    suspend fun getExample(query: String, page: Int, perPage: Int): ExampleDto

}