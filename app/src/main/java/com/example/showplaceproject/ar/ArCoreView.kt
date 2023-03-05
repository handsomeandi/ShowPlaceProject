package com.example.showplaceproject.ar


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.example.showplaceproject.databinding.ArCoreLayoutBinding
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment


@Composable
fun ArCoreView(
    isModelAdded: Boolean,
    model: ModelRenderable,
    modifier: Modifier = Modifier
) {

    AndroidViewBinding(
        modifier = modifier,
        factory = ArCoreLayoutBinding::inflate,
        update = {
            val arFragment = fragmentContainerView.getFragment<ArFragment>()
            arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
//                if (!isModelAdded) {
//                    arFragment.arSceneView.scene.addChild(AnchorNode(hitResult.createAnchor()).apply {
//                        addChild(TransformableNode(arFragment.transformationSystem).apply {
//                            renderable = model
//                        })
//                    })
                arFragment.arSceneView.scene.addChild(Node().apply {
                    renderable = model
                })
//                }
            }
        }
    )
}