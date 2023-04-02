package com.example.showplaceproject.data.di

import com.example.showplaceproject.data.db.*
import com.example.showplaceproject.data.db.datasource.LocalDataSource
import com.example.showplaceproject.data.db.datasource.LocalDataSourceImpl
import com.example.showplaceproject.data.db.repository.GeoLocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                GeoInfoObject::class, TextObject::class, PhotoObject::class,
                MetadataObject::class,
                ArModelObject::class,
                AudioObject::class,
            )
        )
            .compactOnLaunch()
            .build()
        return Realm.open(config)
    }

    @Singleton
    @Provides
    fun provideMongoRepository(dataSource: LocalDataSource): GeoLocalRepository {
        return GeoLocalRepository(dataSource = dataSource)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(realm: Realm): LocalDataSource {
        return LocalDataSourceImpl(realm = realm)
    }

}