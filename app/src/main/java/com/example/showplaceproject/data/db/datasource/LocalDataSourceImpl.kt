package com.example.showplaceproject.data.db.datasource

import com.example.showplaceproject.data.db.GeoInfoObject
import io.realm.kotlin.Realm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import io.realm.kotlin.ext.query

class LocalDataSourceImpl(val realm: Realm): LocalDataSource {
    override fun getGeoData(): Flow<GeoInfoObject> {
        return realm.query<GeoInfoObject>().asFlow().map {
            it.list.first()
        }
    }

    override suspend fun insertGeoData(data: GeoInfoObject) {
        realm.write { copyToRealm(data) }
    }

    override suspend fun removeGeoData() {
        realm.write {
            realm.query<GeoInfoObject>().first().find()?.let { delete(it) }
        }
    }
}