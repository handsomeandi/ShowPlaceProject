package com.example.showplaceproject.data.network

import com.example.showplaceproject.data.network.api.InfoResponseEntity
import com.example.showplaceproject.domain.InfoResponseModel

object Mapper {
    fun InfoResponseEntity.toModel() = InfoResponseModel (
        models = this.models.map {

        }
            )

    fun
}