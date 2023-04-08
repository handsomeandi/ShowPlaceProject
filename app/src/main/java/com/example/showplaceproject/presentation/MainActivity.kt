package com.example.showplaceproject.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.showplaceproject.presentation.audio.AudioScreen
import com.example.showplaceproject.presentation.mainscreen.MainScreen
import com.example.showplaceproject.presentation.media.MediaScreen
import com.example.showplaceproject.presentation.media.photo.PhotosScreen
import com.example.showplaceproject.presentation.navigation.NavigationItem
import com.example.showplaceproject.presentation.theme.ShowPlaceProjectTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint

//TODO: make possible to open obj and types other than glb
@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShowPlaceProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation(rememberNavController())
                }
            }
        }

    }


    @Composable
    fun Navigation(navController: NavHostController) {
        NavHost(navController, startDestination = NavigationItem.Main.route) {
            composable(NavigationItem.Main.route) {
                MainScreen(navController)
            }
            composable(NavigationItem.Audio.route) {
                AudioScreen(navController)
            }
            navigation(
                startDestination = NavigationItem.MediaMain.route,
                route = NavigationItem.Media.route
            ) {
                composable(NavigationItem.MediaMain.route) {
                    MediaScreen(navController)
                }
                composable("${NavigationItem.Photo.route}?photos={photos}",
                    arguments = listOf(
                        navArgument("photos") {
                            type = NavType.StringType
                            defaultValue = "[]"
                        }
                    )) { from ->
                    val gson = Gson()
                    val list: List<String> = gson.fromJson(
                        from.arguments?.getString("photos"),
                        object : TypeToken<List<String>>() {}.type
                    )
                    PhotosScreen(photos = list)
                }
            }

        }
    }


}



