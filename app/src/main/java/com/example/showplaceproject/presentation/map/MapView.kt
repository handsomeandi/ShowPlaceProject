package com.example.showplaceproject.presentation.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.example.showplaceproject.R
import com.example.showplaceproject.core.getBitmapFromVectorDrawable
import com.example.showplaceproject.databinding.MapLayoutBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider


@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
) {
    var mapView: MapView? = null
    DisposableEffect(Unit) {
        onDispose {
            mapView?.onStop()
        }
    }
    val context = LocalContext.current
    AndroidViewBinding(
        modifier = modifier,
        factory = MapLayoutBinding::inflate,
        update = {
            mapView = mapview
            mapView?.onStart()
            mapview.map.move(
                CameraPosition(Point(44.974352, 34.098238), 20.0f, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH, 0.5f),
                null
            )
            mapview.map.mapObjects.addPlacemark(Point(44.974352, 34.098238), ImageProvider.fromResource(context, R.drawable.image_marker))
        }
    )
}