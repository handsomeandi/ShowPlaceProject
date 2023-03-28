package com.example.showplaceproject.domain

data class InfoResponseModel(
    val models: List<ModelModel>? = null,
    val weather: List<AudioModel>? = null,
    val text: List<TextModel>,
    val photos: List<PhotoModel>,
    val metadata: MetadataModel
)

data class TextModel(
    val id: String,
    val name: String,
    val content: String
)

data class PhotoModel(
    val id: String,
    val name: String,
    val file: String
)

data class MetadataModel(
    val total: Int
)

data class ModelModel(
    var id: Int,
    var name: String? = null,
    var file: String? = null,
    var longitude: Double? = null,
    var latitude: Double? = null
)


data class AudioModel(
    var id: Int,
    var name: String? = null,
    var file: String? = null,
    var longitude: Double? = null,
    var latitude: Double? = null
)

data class WeatherModel(
    var description: String? = null,
    var icon: String? = null
)

data class WindModel(
    var speed: String? = null
)

data class SunModel(
    var sunrise: Long = 0,
    var sunset: Long = 0
)