package com.example.showplaceproject.data.network.repository

import com.example.showplaceproject.data.network.Mapper.toModel
import com.example.showplaceproject.data.network.datasource.GeoRemoteDataSource
import com.example.showplaceproject.domain.GeoInfoModel
import javax.inject.Inject

class GeoRemoteRepository @Inject constructor(
    private val remoteDataSource: GeoRemoteDataSource
) {
    suspend fun getGeoData(lat: Double, lng: Double): GeoInfoModel {
        val response = remoteDataSource.getGeoData(lat, lng)
        return response.toModel()
    }


}