package com.example.showplaceproject

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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
                    Box(modifier = Modifier
                        .fillMaxSize()) {
                        val modelObject by model.observeAsState()
                        modelObject?.let {
                            ArCoreView(
                                model = it,
                                onUpdateListener = ::onUpdate
                            )
                        }

                        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                            val centerImage = createRef()
                            Image(
                                painter = painterResource(id = R.drawable.borders),
                                contentDescription = "borders",
                                modifier = Modifier
                                    .padding(top = 45.dp, bottom = 38.dp)
                                    .constrainAs(centerImage) {
                                        centerHorizontallyTo(parent)
                                        centerVerticallyTo(parent)
                                    }
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_geo),
                                contentDescription = "Left Image",
                                modifier = Modifier
                                    .constrainAs(createRef()) {
                                        start.linkTo(centerImage.start)
                                        bottom.linkTo(centerImage.top)
                                    }
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_question),
                                contentDescription = "Right Image",
                                modifier = Modifier
                                    .constrainAs(createRef()) {
                                        end.linkTo(centerImage.end)
                                        bottom.linkTo(centerImage.top)
                                    }
                            )
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .constrainAs(createRef()) {
                                    bottom.linkTo(parent.bottom)
                                }) {
                                ConstraintLayout(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {

                                    val bottomCenterImage = createRef()
                                    val backgroundImage = createRef()
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_bottom_background),
                                        contentDescription = "background",
                                        contentScale = ContentScale.FillWidth,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .constrainAs(backgroundImage) {
                                                bottom.linkTo(parent.bottom)
                                            }
                                            .offset(y = (8).dp)
                                    )

                                    Image(
                                        painter = painterResource(id = R.drawable.ic_audio),
                                        contentDescription = "Bottom Left Image",
                                        modifier = Modifier.constrainAs(createRef()) {

                                            end.linkTo(bottomCenterImage.start)
                                            start.linkTo(parent.start)
                                            top.linkTo(backgroundImage.top)
                                            bottom.linkTo(backgroundImage.bottom)
                                        }
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_circle),
                                        contentDescription = "Bottom Center Image",
                                        modifier = Modifier
                                            .constrainAs(bottomCenterImage) {
                                                end.linkTo(parent.end)
                                                start.linkTo(parent.start)
                                                bottom.linkTo(backgroundImage.bottom)
                                            }
                                            .padding(bottom = 30.dp)
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_media),
                                        contentDescription = "Bottom Right Image",
                                        modifier = Modifier.constrainAs(createRef()) {
                                            end.linkTo(parent.end)
                                            start.linkTo(bottomCenterImage.end)
                                            top.linkTo(backgroundImage.top)
                                            bottom.linkTo(backgroundImage.bottom)
                                        }
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
        val uri =
//            "test.obj"
//            "test.glb"
            "https://github.com/google/model-viewer/blob/master/packages/shared-assets/models/Astronaut.glb?raw=true"
//            "https://github.com/eduter/sokobot-3d/raw/master/public/3d-models/robot.glb"
//            "https://github.com/KhronosGroup/glTF-Sample-Models/blob/master/1.0/Box/glTF/Box.gltf"
        getModelForExercise(uri)
//        lifecycleScope.launch(Dispatchers.Main) {
//
//        }

    }

    @Preview
    @Composable
    private fun Preview() {
        ShowPlaceProjectTheme {
            // A surface container using the 'background' color from the theme
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                Box {
                    val modelObject by model.observeAsState()
                    modelObject?.let {
                        ArCoreView(
                            model = it,
                            onUpdateListener = ::onUpdate
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.borders),
                        contentDescription = "borders",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .wrapContentSize()
                    )
                }
            }
        }
    }

    private fun onUpdate(frameTime: FrameTime?, fragment: ArFragment, modelRenderable: ModelRenderable) {
        //get the frame from the scene for shorthand
        val frame = fragment.arSceneView.arFrame
        if (frame != null) {
            //get the trackables to ensure planes are detected
            val var3 = frame.getUpdatedTrackables(Plane::class.java).iterator()
            while (var3.hasNext()) {
                val plane = var3.next() as Plane

                //If a plane has been detected & is being tracked by ARCore
                if (plane.trackingState == TrackingState.TRACKING) {


                    //Get all added anchors to the frame
                    val iterableAnchor = frame.updatedAnchors.iterator()

                    //place the first object only if no previous anchors were added
                    if (!iterableAnchor.hasNext()) {
                        //Perform a hit test at the center of the screen to place an object without tapping
                        val hitTest = frame.hitTest(frame.screenCenter().x, frame.screenCenter().y)

                        //iterate through all hits
                        val hitTestIterator = hitTest.iterator()
                        while (hitTestIterator.hasNext()) {
                            if (isModelAdded) break
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
                            transformableNode.worldPosition = Vector3(
                                modelAnchor.pose.tx(),
                                modelAnchor.pose.compose(Pose.makeTranslation(0f, 0.05f, 0f)).ty(),
                                modelAnchor.pose.tz()
                            )
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
