package com.challenge.sparkgallery.android

import android.content.Context
import android.support.multidex.MultiDexApplication
import android.support.multidex.MultiDex




/**
 * Created by 805640 on 05.02.2018.
 */
class CYAApplication : MultiDexApplication() {
    companion object {
        lateinit var applicationComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}