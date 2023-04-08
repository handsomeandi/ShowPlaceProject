package com.example.showplaceproject.core

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.filament.utils.Float3
import com.google.ar.core.Frame
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Vector3

fun Node.setCenter(origin: Float3 = Float3(0f, 0f, 0f)) {
    renderableInstance?.filamentAsset?.let { asset ->
        val center = asset.boundingBox.center.let { v -> Float3(v[0], v[1], v[2]) }
        val halfExtent = asset.boundingBox.halfExtent.let { v -> Float3(v[0], v[1], v[2]) }
        val fCenter = -(center + halfExtent * origin) * Float3(1f, 1f, 1f)
        localPosition = Vector3(fCenter.x, fCenter.y, fCenter.z)
    }
}

object Constants {
    const val DEFAULT_RADIUS = 1f
}

fun Context.getActivity(): Activity? =
    when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.getActivity()
        else -> null
    }

fun Context.hideSystemUi() {
    val activity = this.getActivity() ?: return
    val window = activity.window ?: return
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, window.decorView).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

fun Context.showSystemUi() {
    val activity = this.getActivity() ?: return
    val window = activity.window ?: return
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(
        window,
        window.decorView
    ).show(WindowInsetsCompat.Type.systemBars())
}

operator fun Float3.plus(v: Float3) = Float3(x + v.x, y + v.y, z + v.z)
operator fun Float3.minus(v: Float3) = Float3(x - v.x, y - v.y, z - v.z)
operator fun Float3.times(v: Float3) = Float3(x * v.x, y * v.y, z * v.z)
operator fun Float3.unaryMinus() = Float3(-x, -y, -z)

fun Frame.screenCenter(view: View): Vector3 {
    return Vector3(view.width / 2f, view.height / 2f, 0f)
}

fun formatTime(milliseconds: Long): String {
    val minutes = (milliseconds / 1000) / 60
    val seconds = (milliseconds / 1000) % 60
    return "$minutes:$seconds"
}
