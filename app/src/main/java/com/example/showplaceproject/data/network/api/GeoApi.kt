package com.example.showplaceproject.data.network.api

import com.example.showplaceproject.data.network.AppUrls
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoApi {
    @GET(AppUrls.getInfoUrl)
    suspend fun getGeoData(@Query("lat") lat: Double, @Query("lng") lng: Double): InfoResponseEntity

}