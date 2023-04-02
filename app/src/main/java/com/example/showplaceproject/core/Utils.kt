package com.example.showplaceproject.core

import android.view.View
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

operator fun Float3.plus(v: Float3) = Float3(x + v.x, y + v.y, z + v.z)
operator fun Float3.minus(v: Float3) = Float3(x - v.x, y - v.y, z - v.z)
operator fun Float3.times(v: Float3) = Float3(x * v.x, y * v.y, z * v.z)
operator fun Float3.unaryMinus() = Float3(-x, -y, -z)

fun Frame.screenCenter(view: View): Vector3 {
    return Vector3(view.width / 2f, view.height / 2f, 0f)
}
fun formatTime(milliseconds: Int): String {
    val minutes = (milliseconds / 1000) / 60
    val seconds = (milliseconds / 1000) % 60
    return "$minutes:$seconds"
}
