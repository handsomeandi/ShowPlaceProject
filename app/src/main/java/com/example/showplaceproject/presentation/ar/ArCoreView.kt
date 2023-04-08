package com.example.showplaceproject.presentation.ar


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.lifecycle.ViewModel
import com.example.showplaceproject.databinding.ArCoreLayoutBinding
import com.example.showplaceproject.presentation.mainscreen.MainScreenViewModel
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment


@Composable
fun ArCoreView(
    model: ModelRenderable?,
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel,
    onUpdateListener: (FrameTime?, ArFragment, ModelRenderable, MainScreenViewModel) -> Unit
) {

    AndroidViewBinding(
        modifier = modifier,
        factory = ArCoreLayoutBinding::inflate,
        update = {
            val arFragment = fragmentContainerView.getFragment<ArFragment>()
            arFragment.arSceneView.scene.addOnUpdateListener { frameTime ->
                model?.let {
                    onUpdateListener(frameTime, arFragment, model, viewModel)
                }
            }
        }
    )
}