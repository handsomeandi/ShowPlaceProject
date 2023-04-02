package com.example.showplaceproject.data.db.datasource

import com.example.showplaceproject.data.db.GeoInfoObject
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getGeoData(): Flow<GeoInfoObject>

    suspend fun insertGeoData(data: GeoInfoObject)

    suspend fun removeGeoData()
}