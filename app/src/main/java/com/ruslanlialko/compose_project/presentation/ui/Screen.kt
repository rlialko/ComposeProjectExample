package com.ruslanlialko.compose_project.presentation.ui

sealed class Screen(val route: String) {
    object List : Screen("list_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
