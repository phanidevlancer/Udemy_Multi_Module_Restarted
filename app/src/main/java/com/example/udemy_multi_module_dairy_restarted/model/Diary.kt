package com.example.udemy_multi_module_dairy_restarted.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class Diary : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.create()
    var ownerId: String = ""
    var title: String = ""
    var mood: String = Mood.Neutral.name
    var description: String = ""
    var images: RealmList<String> = realmListOf()
    var date: RealmInstant = RealmInstant.from(System.currentTimeMillis(), 0)
}