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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.showplaceproject.presentation.audio.AudioScreen
import com.example.showplaceproject.presentation.mainscreen.MainScreen
import com.example.showplaceproject.presentation.navigation.NavigationItem
import com.example.showplaceproject.presentation.theme.ShowPlaceProjectTheme
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

        }
    }


}



