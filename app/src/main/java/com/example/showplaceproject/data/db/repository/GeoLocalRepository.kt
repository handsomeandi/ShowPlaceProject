package com.example.showplaceproject.data.db.repository

import com.example.showplaceproject.data.db.datasource.LocalDataSource
import com.example.showplaceproject.data.network.Mapper.toDbObject
import com.example.showplaceproject.data.network.Mapper.toModel
import com.example.showplaceproject.domain.GeoInfoModel
import javax.inject.Inject

class GeoLocalRepository @Inject constructor(private val dataSource: LocalDataSource) {

    fun getGeoData(): GeoInfoModel? {
        return dataSource.getGeoData()?.toModel()

    }

    suspend fun insertGeoData(data: GeoInfoModel) {
        dataSource.insertGeoData(data.toDbObject())
    }

    suspend fun removeGeoData() {
        dataSource.removeGeoData()
    }
}