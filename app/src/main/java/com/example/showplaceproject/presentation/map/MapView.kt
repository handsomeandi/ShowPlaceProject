package com.example.showplaceproject.presentation.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.showplaceproject.R
import com.example.showplaceproject.core.getBitmapFromVectorDrawable
import com.example.showplaceproject.databinding.MapLayoutBinding
import com.example.showplaceproject.presentation.mainscreen.MainScreenViewModel
import com.example.showplaceproject.presentation.mainscreen.getModelForExercise
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider


@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: MapViewModel = hiltViewModel()
    val points = viewModel.points.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(points) {
        viewModel.init()
    }
    var mapView: MapView? = null
    DisposableEffect(Unit) {
        onDispose {
            mapView?.onStop()
        }
    }
    AndroidViewBinding(
        modifier = modifier,
        factory = MapLayoutBinding::inflate,
        update = {
            mapView = mapview
            mapView?.onStart()
            mapview.map.move(
                CameraPosition(Point(
                    45.042897, 34.283206), 18.0f, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH, 0.5f),
                null
            )
            points.value?.forEach {
                mapview.map.mapObjects.addPlacemark(it, ImageProvider.fromResource(context, R.drawable.image_marker))
            }
        }
    )
}