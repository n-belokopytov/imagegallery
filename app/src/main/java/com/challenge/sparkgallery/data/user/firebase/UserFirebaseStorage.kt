package com.challenge.sparkgallery.data.user.firebase

import com.challenge.sparkgallery.data.user.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Created by 805640 on 07.02.2018.
 */
class UserFirebaseStorage {
    companion object {
        fun getCurrentUser() : User? {
            val firebaseUser = FirebaseAuth.getInstance().currentUser
            if (firebaseUser == null) {
                return null
            } else {
                return mapUser(firebaseUser)
            }
        }

        fun mapUser(firebaseUser : FirebaseUser) : User {
            return User(firebaseUser.displayName, firebaseUser.email, firebaseUser.uid)
        }
    }
}