package com.challenge.sparkgallery.data.user

import android.content.SharedPreferences
import com.challenge.sparkgallery.data.user.firebase.UserFirebaseStorage
import com.challenge.sparkgallery.util.IDGenerator
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by 805640 on 06.02.2018.
 */
@Singleton
class UserRepo @Inject constructor(val sharedPreferences : SharedPreferences) {

    private val VISITOR_ID = "USER_VISITOR_ID"

    var user = UserFirebaseStorage.getCurrentUser()

    val visitorId by lazy {
        val stored = sharedPreferences.getString(VISITOR_ID, null)
        if (stored == null) {
            val generated = IDGenerator.generateVisitorId()
            sharedPreferences.edit().putString(VISITOR_ID, generated).apply()
            generated
        } else {
            stored
        }
    }
}