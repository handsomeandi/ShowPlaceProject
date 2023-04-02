package com.example.showplaceproject.data.network

import com.example.showplaceproject.data.db.*
import com.example.showplaceproject.data.network.api.*
import com.example.showplaceproject.domain.*
import io.realm.kotlin.ext.toRealmList

object Mapper {
    fun InfoResponseEntity.toModel() = GeoInfoModel(
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
    //////
    fun GeoInfoObject.toModel() = GeoInfoModel(
        models = this.models?.map {
            it.toModel()
        },
        audio = this.audio?.map {
            it.toModel()
        },
        text = this.text?.map {
            it.toModel()
        } ?: emptyList(),
        photos = this.photos.map {
            it.toModel()
        },
        metadata = this.metadata?.toModel()
    )

    private fun ArModelObject.toModel() = ArModelModel(
        id = this.id,
        name = this.name,
        file = this.file,
        longitude = this.longitude,
        latitude = this.latitude,
    )

    private fun AudioObject.toModel() = AudioModel(
        id = this.id,
        name = this.name,
        file = this.file,
        longitude = this.longitude,
        latitude = this.latitude,
    )

    private fun TextObject.toModel() = TextModel(
        id = this.id,
        name = this.name,
        content = this.content
    )

    private fun PhotoObject.toModel() = PhotoModel(
        id = this.id,
        name = this.name,
        file = this.file
    )

    private fun MetadataObject.toModel() = MetadataModel(
        total = this.total
    )
    fun GeoInfoModel.toDbObject() = GeoInfoObject().apply {
        models = this@toDbObject.models?.map {
            it.toDbObject()
        }?.toRealmList()
        audio = this@toDbObject.audio?.map {
            it.toDbObject()
        }?.toRealmList()
        text = this@toDbObject.text.map {
            it.toDbObject()
        }.toRealmList()
        photos = this@toDbObject.photos.map {
            it.toDbObject()
        }.toRealmList()
        metadata = this@toDbObject.metadata?.toDbObject()
    }

    private fun ArModelModel.toDbObject() = ArModelObject().apply {
        id = this@toDbObject.id
        name = this@toDbObject.name
        file = this@toDbObject.file
        longitude = this@toDbObject.longitude
        latitude = this@toDbObject.latitude
    }

    private fun AudioModel.toDbObject() = AudioObject().apply {
        id = this@toDbObject.id
        name = this@toDbObject.name
        file = this@toDbObject.file
        longitude = this@toDbObject.longitude
        latitude = this@toDbObject.latitude
    }

    private fun TextModel.toDbObject() = TextObject().apply {
        id = this@toDbObject.id
        name = this@toDbObject.name
        content = this@toDbObject.content
    }

    private fun PhotoModel.toDbObject() = PhotoObject().apply {
        id = this@toDbObject.id
        name = this@toDbObject.name
        file = this@toDbObject.file
    }

    private fun MetadataModel.toDbObject() = MetadataObject().apply {
        total = this@toDbObject.total
    }
}