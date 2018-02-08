package com.challenge.sparkgallery.data.user

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by 805640 on 07.02.2018.
 */
@Module
class UserModule {
    @Provides
    @Singleton
    fun getSharedPreferences(context : Context) : SharedPreferences {
        return context.getSharedPreferences(context.packageName, MODE_PRIVATE)
    }
}