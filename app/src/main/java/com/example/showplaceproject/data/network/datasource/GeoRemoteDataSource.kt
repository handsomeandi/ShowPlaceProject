package com.example.showplaceproject.data.network.datasource

import com.example.showplaceproject.data.network.api.GeoApi
import com.example.showplaceproject.data.network.api.InfoResponseEntity
import javax.inject.Inject

class GeoRemoteDataSource @Inject constructor(private val api: GeoApi) {

    suspend fun getGeoData(lat: Double, lng: Double): InfoResponseEntity {
        return api.getGeoData(lat, lng)
    }

}