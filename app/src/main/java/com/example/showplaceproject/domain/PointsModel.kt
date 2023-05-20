package com.example.showplaceproject.domain

data class PointsModel(
    val points: List<PointModel>
)

data class PointModel(
    val lat: Double,
    val lon: Double
)
