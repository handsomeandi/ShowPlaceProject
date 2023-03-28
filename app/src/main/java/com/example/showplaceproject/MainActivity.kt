package com.example.showplaceproject

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.showplaceproject.audio.AudioScreen
import com.example.showplaceproject.mainscreen.MainScreen
import com.example.showplaceproject.mainscreen.getModelForExercise
import com.example.showplaceproject.mainscreen.model
import com.example.showplaceproject.navigation.NavigationItem
import com.example.showplaceproject.ui.theme.ShowPlaceProjectTheme

//TODO: make possible to open obj and types other than glb
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
        val uri =
//            "test.obj"
//            "test.glb"
            "https://github.com/google/model-viewer/blob/master/packages/shared-assets/models/Astronaut.glb?raw=true"
//            "https://github.com/eduter/sokobot-3d/raw/master/public/3d-models/robot.glb"
//            "https://github.com/KhronosGroup/glTF-Sample-Models/blob/master/1.0/Box/glTF/Box.gltf"
        getModelForExercise(this, model, uri)

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



