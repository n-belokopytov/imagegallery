package com.challenge.sparkgallery.android

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by 805640 on 07.02.2018.
 */
@Module
class AppModule(val application: CYAApplication) {
    @Provides
    fun getContext() : Context {
        return application
    }
}