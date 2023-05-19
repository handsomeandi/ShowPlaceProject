package com.example.showplaceproject.presentation.mainscreen

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.showplaceproject.R
import com.example.showplaceproject.core.screenCenter
import com.example.showplaceproject.presentation.SelectedScreen
import com.example.showplaceproject.presentation.ar.ArCoreView
import com.example.showplaceproject.presentation.bottomnav.ShowPlaceBottomNavigation
import com.example.showplaceproject.presentation.navigation.NavigationItem
import com.google.ar.core.Plane
import com.google.ar.core.Pose
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode


@Composable
fun MainScreen(navHostController: NavHostController) {
    val viewModel: MainScreenViewModel = hiltViewModel()
    val modelObject by viewModel.model.observeAsState()
    val geoObject by viewModel.geoModel.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(geoObject) {
        viewModel.init()
        getModelForExercise(context, viewModel.model, geoObject?.models?.first()?.file)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ArCoreView(
            model = modelObject,
            viewModel = viewModel,
            onUpdateListener = ::onUpdate
        )
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
                    }.clickable {
                        navHostController.navigate(NavigationItem.Map.route)
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
                ShowPlaceBottomNavigation(SelectedScreen.MAIN, navHostController)
            }

        }
    }

}

private fun onUpdate(
    frameTime: FrameTime?,
    fragment: ArFragment,
    modelRenderable: ModelRenderable,
    viewModel: MainScreenViewModel
) {
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
                    val hitTest = frame.hitTest(
                        frame.screenCenter(fragment.requireView()).x,
                        frame.screenCenter(fragment.requireView()).y
                    )

                    //iterate through all hits
                    val hitTestIterator = hitTest.iterator()
                    while (hitTestIterator.hasNext()) {
                        if (viewModel.isModelAdded() == true) break
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
                        viewModel.changeModelAdded(true)
                    }
                }
            }
        }
    }
}


fun getModelForExercise(
    context: Context,
    model: MutableLiveData<ModelRenderable>,
    nameModel: String?
) {
    ModelRenderable.builder()
        .setSource(context, Uri.parse(nameModel))
        .setIsFilamentGltf(true)
        .build()
        .thenAccept { renderable ->
            model.value = renderable
        }
}