package com.ruslanlialko.compose_project.data.dto

import com.ruslanlialko.compose_project.domain.model.Example

data class ExampleDto(
    val someValue: Int
)


fun ExampleDto.toRepos(): Example{
    return Example(someValue =  someValue)
}