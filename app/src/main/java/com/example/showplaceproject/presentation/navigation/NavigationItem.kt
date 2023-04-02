package com.example.showplaceproject.presentation.navigation

sealed class NavigationItem(var route: String) {
    object Main : NavigationItem("main")
    object Media : NavigationItem("media")
    object Audio : NavigationItem("audio")
    object Map : NavigationItem("map")
}