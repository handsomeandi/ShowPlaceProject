package com.example.showplaceproject.data.network

import com.example.showplaceproject.data.network.api.*
import com.example.showplaceproject.domain.*

object Mapper {
    fun InfoResponseEntity.toModel() = InfoResponseModel(
        models = this.models?.map {
            it.toModel()
        },
        audio = this.audio?.map {
            it.toModel()
        },
        text = this.text.map {
            it.toModel()
        },
        photos = this.photos.map {
            it.toModel()
        },
        metadata = this.metadata.toModel()
    )

    private fun ModelEntity.toModel() = ArModelModel(
        id = this.id,
        name = this.name,
        file = this.file,
        longitude = this.longitude,
        latitude = this.latitude,
    )

    private fun AudioEntity.toModel() = AudioModel(
        id = this.id,
        name = this.name,
        file = this.file,
        longitude = this.longitude,
        latitude = this.latitude,
    )

    private fun TextEntity.toModel() = TextModel(
        id = this.id,
        name = this.name,
        content = this.content
    )

    private fun PhotoEntity.toModel() = PhotoModel(
        id = this.id,
        name = this.name,
        file = this.file
    )

    private fun MetadataEntity.toModel() = MetadataModel(
        total = this.total
    )
}