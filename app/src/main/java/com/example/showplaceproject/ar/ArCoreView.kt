package com.example.showplaceproject.ar


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.example.showplaceproject.databinding.ArCoreLayoutBinding
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment


@Composable
fun ArCoreView(
    model: ModelRenderable,
    modifier: Modifier = Modifier,
    onUpdateListener: (FrameTime?, ArFragment, ModelRenderable) -> Unit
) {

    AndroidViewBinding(
        modifier = modifier,
        factory = ArCoreLayoutBinding::inflate,
        update = {
            val arFragment = fragmentContainerView.getFragment<ArFragment>()
            arFragment.arSceneView.scene.addOnUpdateListener { frameTime ->
                onUpdateListener(frameTime, arFragment, model)
            }
        }
    )
}