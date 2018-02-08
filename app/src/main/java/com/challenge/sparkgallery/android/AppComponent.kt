package com.challenge.sparkgallery.android

import android.content.Context
import com.challenge.sparkgallery.data.gallery.GalleryModule
import com.challenge.sparkgallery.data.gallery.GalleryRepo
import com.challenge.sparkgallery.data.user.UserModule
import com.challenge.sparkgallery.data.user.UserRepo
import dagger.Component
import javax.inject.Singleton

/**
 * Created by 805640 on 05.02.2018.
 */
@Singleton
@Component(modules = [(AppModule::class), (GalleryModule::class), (UserModule::class)])
interface AppComponent {
    fun galleryRepo(): GalleryRepo
    fun userRepo(): UserRepo
    fun context(): Context
}