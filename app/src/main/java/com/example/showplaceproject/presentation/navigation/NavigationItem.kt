package com.example.showplaceproject.presentation.navigation

sealed class NavigationItem(var route: String) {
    object Main : NavigationItem("main")
    object Media : NavigationItem("media")
    object MediaMain : NavigationItem("mediaMain")
    object Photo : NavigationItem("photo")
    object Video : NavigationItem("video")
    object Audio : NavigationItem("audio")
    object Map : NavigationItem("map")
}