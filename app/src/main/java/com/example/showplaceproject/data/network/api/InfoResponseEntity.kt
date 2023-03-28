package com.example.showplaceproject.data.network.api

import com.google.gson.annotations.SerializedName

data class InfoResponseEntity(
    @SerializedName("models") val models: List<ModelEntity>? = null,
    @SerializedName("audio") val weather: List<AudioEntity>? = null,
    @SerializedName("text") val text: List<TextEntity>,
    @SerializedName("photos") val photos: List<PhotoEntity>,
    @SerializedName("metadata") val metadata: MetadataEntity
)

data class TextEntity(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("content") val content: String
)

data class PhotoEntity(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("file") val file: String
)

data class MetadataEntity(
    @SerializedName("total") val total: Int
)

data class ModelEntity(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String? = null,
    @SerializedName("file") var file: String? = null,
    @SerializedName("longitude") var longitude: Double? = null,
    @SerializedName("latitude") var latitude: Double? = null
)


data class AudioEntity(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String? = null,
    @SerializedName("file") var file: String? = null,
    @SerializedName("longitude") var longitude: Double? = null,
    @SerializedName("latitude") var latitude: Double? = null
)

data class WeatherEntity(
    @SerializedName("description") var description: String? = null,
    @SerializedName("icon") var icon: String? = null
)

data class WindEntity(
    @SerializedName("speed") var speed: String? = null
)

data class SunEntity(
    @SerializedName("sunrise") var sunrise: Long = 0,
    @SerializedName("sunset") var sunset: Long = 0
)