package com.example.showplaceproject.data.db.datasource

import com.example.showplaceproject.data.db.GeoInfoObject

interface LocalDataSource {
    fun getGeoData(): GeoInfoObject?

    suspend fun insertGeoData(data: GeoInfoObject)

    suspend fun removeGeoData()
}