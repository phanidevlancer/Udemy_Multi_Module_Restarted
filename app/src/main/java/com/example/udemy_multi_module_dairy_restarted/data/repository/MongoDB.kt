package com.example.udemy_multi_module_dairy_restarted.data.repository

import com.example.udemy_multi_module_dairy_restarted.model.Diary
import com.example.udemy_multi_module_dairy_restarted.model.RequestState
import com.example.udemy_multi_module_dairy_restarted.utils.Constants.APP_ID
import com.example.udemy_multi_module_dairy_restarted.utils.toInstant
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import io.realm.kotlin.query.Sort
import io.realm.kotlin.types.RealmInstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.ZoneId

object MongoDB : MongoRepository {

    private val app = App.create(APP_ID)
    private val user = app.currentUser
    private lateinit var realm: Realm

    init {
        configureTheRealm()
    }

    override fun configureTheRealm() {
        user?.let { realmUser ->
            val config =
                SyncConfiguration.Builder(user, setOf(Diary::class)).initialSubscriptions { sub ->
                    add(
                        query = sub.query<Diary>(query = "ownerId == $0", user.identity),
                        name = "User's Dairies"
                    )
                }.log(LogLevel.ALL).build()
            realm = Realm.open(config)
        }
    }

    private fun getDummyResponse(): List<Diary> {
        val diary = Diary()
        val diary1 = Diary()
        val list = mutableListOf<Diary>()
        diary.title = "my title"
        diary.description =
            "\uD83C\uDF1F Once, a tomato \uD83C\uDF45 tried stand-up comedy for the pizza \uD83C\uDF55. The avocado \uD83E\uDD51 played guitar, and everyone laughed! \uD83D\uDE04 The end."
        diary.mood = "Happy"
        diary.ownerId = "6560b1c206187aa330aeea33"
        diary.date = RealmInstant.from(System.currentTimeMillis(), 0)

        diary1.title = "my title"
        diary1.description =
            "\uD83C\uDF1F Once, a tomato \uD83C\uDF45 tried stand-up comedy for the pizza \uD83C\uDF55. The avocado \uD83E\uDD51 played guitar, and everyone laughed! \uD83D\uDE04 The end."
        diary1.mood = "Happy"
        diary1.ownerId = "6560b1c206187aa330aeea33"
        diary1.date = RealmInstant.from(System.currentTimeMillis(), 0)
        diary1.images = realmListOf(
            "https://statusneo.com/wp-content/uploads/2023/02/MicrosoftTeams-image551ad57e01403f080a9df51975ac40b6efba82553c323a742b42b1c71c1e45f1.jpg",
            "https://statusneo.com/wp-content/uploads/2023/02/MicrosoftTeams-image551ad57e01403f080a9df51975ac40b6efba82553c323a742b42b1c71c1e45f1.jpg",
            "https://statusneo.com/wp-content/uploads/2023/02/MicrosoftTeams-image551ad57e01403f080a9df51975ac40b6efba82553c323a742b42b1c71c1e45f1.jpg",
            "https://statusneo.com/wp-content/uploads/2023/02/MicrosoftTeams-image551ad57e01403f080a9df51975ac40b6efba82553c323a742b42b1c71c1e45f1.jpg",
            "https://statusneo.com/wp-content/uploads/2023/02/MicrosoftTeams-image551ad57e01403f080a9df51975ac40b6efba82553c323a742b42b1c71c1e45f1.jpg",
            "https://statusneo.com/wp-content/uploads/2023/02/MicrosoftTeams-image551ad57e01403f080a9df51975ac40b6efba82553c323a742b42b1c71c1e45f1.jpg",
            "https://statusneo.com/wp-content/uploads/2023/02/MicrosoftTeams-image551ad57e01403f080a9df51975ac40b6efba82553c323a742b42b1c71c1e45f1.jpg",
            "https://statusneo.com/wp-content/uploads/2023/02/MicrosoftTeams-image551ad57e01403f080a9df51975ac40b6efba82553c323a742b42b1c71c1e45f1.jpg",
            "https://statusneo.com/wp-content/uploads/2023/02/MicrosoftTeams-image551ad57e01403f080a9df51975ac40b6efba82553c323a742b42b1c71c1e45f1.jpg"
        )
        list.add(diary)
        list.add(diary1)
        return list
    }

    override fun getAllDiaries(): Flow<Diaries> {
        return if (user != null) {
            try {
                println("my user id is : ${realm.configuration.path}")
                realm.configuration.schema
                realm.query<Diary>(query = "ownerId == $0", user.identity)
                    .sort(property = "date", sortOrder = Sort.DESCENDING).asFlow().map { result ->
                        println("my user id is : ${result.list}")
                        RequestState.Success(data = if (result.list.isEmpty()) {
                            val dummyList = getDummyResponse()
                            dummyList.groupBy {
                                it.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                            }
                        } else {
                            result.list.groupBy {
                                it.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                            }
                        })
                    }
            } catch (e: Exception) {
                flow { emit(RequestState.Error(e)) }
            }
        } else {
            flow { emit(RequestState.Error(UserNotAuthenticated())) }
        }
    }


}

private class UserNotAuthenticated : Exception("User is not logged In")