package com.example.showplaceproject.data.network.repository

import com.example.showplaceproject.data.network.datasource.WeatherRemoteDataSource
import com.example.showplaceproject.domain.InfoResponseModel
import com.example.weatherv2.domain.model.TownWeather
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource
) {
    suspend fun getGeoData(lat: Double, lng: Double): InfoResponseModel {
        val response = remoteDataSource.getGeoData(lat, lng)
        return response.toModel()
    }


}