package com.example.showplaceproject.data.db.repository

import com.example.showplaceproject.data.db.datasource.LocalDataSource
import com.example.showplaceproject.data.network.Mapper.toDbObject
import com.example.showplaceproject.data.network.Mapper.toModel
import com.example.showplaceproject.domain.InfoResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GeoLocalRepository @Inject constructor(private val dataSource: LocalDataSource) {

    fun getGeoData(): Flow<InfoResponseModel> {
        return dataSource.getGeoData().map {
            it.toModel()
        }
    }

    suspend fun insertGeoData(data: InfoResponseModel) {
        dataSource.insertGeoData(data.toDbObject())
    }

    suspend fun removeGeoData() {
        dataSource.removeGeoData()
    }
}