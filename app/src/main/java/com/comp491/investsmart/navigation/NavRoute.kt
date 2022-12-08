package com.comp491.investsmart.navigation

sealed class NavRoute(val route: String) {

    object Home: NavRoute("home")

    object Search: NavRoute("search")

    object Favourites: NavRoute("favourites")

    object Profile: NavRoute("profile")

    object Settings: NavRoute("settings")

    object Login: NavRoute("login")

    object Comments: NavRoute("comments")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }

    fun withArgsFormat(vararg args: String) : String {
        return buildString {
            append(route)
            args.forEach{ arg ->
                append("/{$arg}")
            }
        }
    }
}