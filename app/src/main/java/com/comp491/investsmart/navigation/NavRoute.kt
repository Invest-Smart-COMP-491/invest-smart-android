package com.comp491.investsmart.navigation

sealed class NavRoute(val route: String) {

    object Home: NavRoute("home")

    object Search: NavRoute("search")

    object Favourites: NavRoute("favourites")

    object Profile: NavRoute("profile")

    object Settings: NavRoute("settings")

    object Login: NavRoute("login")

    object Comments: NavRoute("comments") {
        const val commentId = "comment_id"
    }

    object AssetDetail: NavRoute("asset_detail") {
        const val assetTicker = "asset_ticker"
    }

    object WebPage: NavRoute("web_page") {
        const val url = "url"
    }

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