package com.example.showplaceproject.data.network.repository

import com.example.showplaceproject.data.network.Mapper.toModel
import com.example.showplaceproject.data.network.datasource.GeoRemoteDataSource
import com.example.showplaceproject.domain.InfoResponseModel
import javax.inject.Inject

class GeoRepository @Inject constructor(
    private val remoteDataSource: GeoRemoteDataSource
) {
    suspend fun getGeoData(lat: Double, lng: Double): InfoResponseModel {
        val response = remoteDataSource.getGeoData(lat, lng)
        return response.toModel()
    }


}