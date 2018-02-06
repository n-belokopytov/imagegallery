package com.challenge.sparkgallery.android

import com.challenge.sparkgallery.data.gallery.GalleryModule
import com.challenge.sparkgallery.data.gallery.GalleryRepo
import dagger.Component
import javax.inject.Singleton

/**
 * Created by 805640 on 05.02.2018.
 */
@Singleton
@Component(modules = [(GalleryModule::class)])
interface AppComponent {
    fun galleryRepo(): GalleryRepo

}