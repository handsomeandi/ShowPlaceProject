package com.example.showplaceproject

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import com.example.showplaceproject.ar.ArCoreView
import com.example.showplaceproject.ui.theme.ShowPlaceProjectTheme
import com.google.ar.sceneform.rendering.ModelRenderable

class MainActivity : FragmentActivity() {
    val model: MutableLiveData<ModelRenderable> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShowPlaceProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Box {
                        val modelObject by model.observeAsState()
                        modelObject?.let {
                            ArCoreView(
                                model = it,
                                isModelAdded = modelObject != null,
                            )
                        }
                    }
                }
            }
        }
        val uri =
            "test.obj"
//            "https://github.com/KhronosGroup/glTF-Sample-Models/blob/master/1.0/Box/glTF/Box.gltf"
        getModelForExercise(uri)
//        lifecycleScope.launch(Dispatchers.Main) {
//
//        }


    }
    private fun getModelForExercise(nameModel: String) {
         ModelRenderable.builder()
             .setSource(this, Uri.parse(nameModel))
             .build()
             .thenAccept { renderable ->
                 model.value = renderable
             }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShowPlaceProjectTheme {
        Greeting("Android")
    }
}