package com.fransbudikashira.suitmediatest.ui.navigation

sealed class Screen(val route: String) {
    object First: Screen("first")
    object Second: Screen("first/{name}") {
        fun createRoute(name: String) = "first/${name}"
    }
    object Third: Screen("third")
}