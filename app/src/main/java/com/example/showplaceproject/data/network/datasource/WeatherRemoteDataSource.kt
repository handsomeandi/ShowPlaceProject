package com.example.showplaceproject.data.network.datasource

import com.example.showplaceproject.data.network.api.GeoApi
import com.example.showplaceproject.data.network.api.InfoResponse
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(private val api: GeoApi) {

    suspend fun getGeoData(lat: Double, lng: Double): InfoResponse {
        return api.getGeoData(lat, lng)
    }

}