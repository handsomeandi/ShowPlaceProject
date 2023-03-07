package com.example.showplaceproject

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
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
import com.google.ar.core.Frame
import com.google.ar.core.Plane
import com.google.ar.core.Pose
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

//TODO: make possible to open obj and types other than glb
class MainActivity : FragmentActivity() {
    val model: MutableLiveData<ModelRenderable> = MutableLiveData()
    var isModelAdded: Boolean = false

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
                                isModelAdded = isModelAdded,
                                onUpdateListener = ::onUpdate
                            )
                        }
                    }
                }
            }
        }
        val uri =
//            "test.obj"
            "https://github.com/eduter/sokobot-3d/raw/master/public/3d-models/robot.glb"
//            "https://github.com/KhronosGroup/glTF-Sample-Models/blob/master/1.0/Box/glTF/Box.gltf"
        getModelForExercise(uri)
//        lifecycleScope.launch(Dispatchers.Main) {
//
//        }


    }

    private fun onUpdate(frameTime: FrameTime?, fragment: ArFragment, modelRenderable: ModelRenderable) {
        //get the frame from the scene for shorthand
        val frame = fragment.arSceneView.arFrame
        if (frame != null) {
            //get the trackables to ensure planes are detected
            val var3 = frame.getUpdatedTrackables(Plane::class.java).iterator()
            while(var3.hasNext()) {
                val plane = var3.next() as Plane

                //If a plane has been detected & is being tracked by ARCore
                if (plane.trackingState == TrackingState.TRACKING) {


                    //Get all added anchors to the frame
                    val iterableAnchor = frame.updatedAnchors.iterator()

                    //place the first object only if no previous anchors were added
                    if(!iterableAnchor.hasNext()) {
                        //Perform a hit test at the center of the screen to place an object without tapping
                        val hitTest = frame.hitTest(frame.screenCenter().x, frame.screenCenter().y)

                        //iterate through all hits
                        val hitTestIterator = hitTest.iterator()
                        while(hitTestIterator.hasNext()) {
                            val hitResult = hitTestIterator.next()

                            //Create an anchor at the plane hit
                            val modelAnchor = plane.createAnchor(hitResult.hitPose)

                            //Attach a node to this anchor with the scene as the parent
                            val anchorNode = AnchorNode(modelAnchor)
                            anchorNode.setParent(fragment.arSceneView.scene)

                            //create a new TranformableNode that will carry our object
                            val transformableNode = TransformableNode(fragment.transformationSystem)
                            transformableNode.setParent(anchorNode)
                            transformableNode.renderable = modelRenderable

                            //Alter the real world position to ensure object renders on the table top. Not somewhere inside.
                            transformableNode.worldPosition = Vector3(modelAnchor.pose.tx(),
                                modelAnchor.pose.compose(Pose.makeTranslation(0f, 0.05f, 0f)).ty(),
                                modelAnchor.pose.tz())
                            isModelAdded = true
                        }
                    }
                }
            }
        }
    }

    private fun Frame.screenCenter(): Vector3 {
        val vw = findViewById<View>(android.R.id.content)
        return Vector3(vw.width / 2f, vw.height / 2f, 0f)
    }

    private fun getModelForExercise(nameModel: String) {
        ModelRenderable.builder()
            .setSource(this, Uri.parse(nameModel))
            .setIsFilamentGltf(true)
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