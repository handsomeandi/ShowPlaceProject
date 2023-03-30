package com.example.showplaceproject.domain

data class InfoResponseModel(
    val models: List<ArModelModel>? = null,
    val audio: List<AudioModel>? = null,
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

data class ArModelModel(
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
