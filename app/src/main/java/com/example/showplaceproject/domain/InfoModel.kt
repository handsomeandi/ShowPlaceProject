package com.example.showplaceproject.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GeoInfoModel(
    val models: List<ArModelModel>? = null,
    val audio: List<AudioModel>? = null,
    val video: List<VideoModel>? = null,
    val text: List<TextModel>,
    val photos: List<PhotoModel>,
    val metadata: MetadataModel?
): Parcelable

@Parcelize
data class TextModel(
    val id: String,
    val name: String,
    val content: String
): Parcelable

@Parcelize
data class PhotoModel(
    val id: String,
    val name: String,
    val file: String
): Parcelable

@Parcelize
data class MetadataModel(
    val total: Int
): Parcelable

@Parcelize
data class ArModelModel(
    var id: Int,
    var name: String? = null,
    var file: String? = null,
    var longitude: Double? = null,
    var latitude: Double? = null
): Parcelable

@Parcelize
data class AudioModel(
    var id: Int,
    var name: String? = null,
    var file: String? = null,
    var longitude: Double? = null,
    var latitude: Double? = null
): Parcelable

@Parcelize
data class VideoModel(
    var id: Int,
    var name: String? = null,
    var file: String? = null,
    var longitude: Double? = null,
    var latitude: Double? = null
): Parcelable
