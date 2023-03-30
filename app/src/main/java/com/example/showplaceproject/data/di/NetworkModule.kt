package com.example.showplaceproject.data.di

import com.example.showplaceproject.data.network.AppUrls
import com.example.showplaceproject.data.network.api.GeoApi
import com.example.showplaceproject.data.network.api.GeoMockApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder().baseUrl(AppUrls.baseUrl).client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()


    @Singleton
    @Provides
    fun provideClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).apply {
            connectTimeout(30, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
        }.build()
    }

    @Singleton
    @Provides
    fun provideLogger(): HttpLoggingInterceptor = HttpLoggingInterceptor()


//    @Singleton
//    @Provides
//    fun provideServerApi(retrofit: Retrofit): GeoApi = retrofit.create(GeoApi::class.java)

    @Singleton
    @Provides
    fun provideMockApi(retrofit: Retrofit): GeoApi = GeoMockApi()

}