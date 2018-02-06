package com.challenge.sparkgallery.android

import android.app.Application

/**
 * Created by 805640 on 05.02.2018.
 */
class CIAApplication : Application() {
    companion object {
        lateinit var applicationComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerAppComponent
                .builder()
                .build()
    }
}