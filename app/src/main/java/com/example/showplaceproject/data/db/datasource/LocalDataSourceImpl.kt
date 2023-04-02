package com.example.showplaceproject.data.db.datasource

import com.example.showplaceproject.data.db.GeoInfoObject
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query

class LocalDataSourceImpl(val realm: Realm): LocalDataSource {
    override fun getGeoData(): GeoInfoObject? {
        return realm.query<GeoInfoObject>().first().find()
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