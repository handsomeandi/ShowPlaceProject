package com.example.showplaceproject.data.network.api

import com.google.gson.annotations.SerializedName

data class PointsResponseEntity(
    @SerializedName("points")
    val points: List<PointEntity>
)

data class PointEntity(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)
