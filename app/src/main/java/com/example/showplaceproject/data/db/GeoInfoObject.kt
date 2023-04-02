package com.example.showplaceproject.data.db

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import kotlin.properties.Delegates

class GeoInfoObject : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var models: RealmList<ArModelObject>? = null
    var audio: RealmList<AudioObject>? = null
    var text: RealmList<TextObject>? = realmListOf()
    var photos: RealmList<PhotoObject> = realmListOf()
    var metadata: MetadataObject? = null
}

class TextObject: EmbeddedRealmObject {
    var id: String = ""
    var name: String = ""
    var content: String = ""
}

class PhotoObject: EmbeddedRealmObject {
    var id: String = ""
    var name: String = ""
    var file: String = ""
}

class MetadataObject: EmbeddedRealmObject {
    var total = 0
}

class ArModelObject: EmbeddedRealmObject {
    var id = -1
    var name: String? = null
    var file: String? = null
    var longitude: Double? = null
    var latitude: Double? = null
}


class AudioObject: EmbeddedRealmObject {
    var id = -1
    var name: String? = null
    var file: String? = null
    var longitude: Double? = null
    var latitude: Double? = null
}