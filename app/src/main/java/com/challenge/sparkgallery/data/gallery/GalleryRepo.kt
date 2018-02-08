package com.challenge.sparkgallery.data.gallery

import com.challenge.sparkgallery.data.gallery.firebase.FirebaseCloudGalleryDb
import com.challenge.sparkgallery.data.gallery.firebase.FirebaseImageStorage
import com.challenge.sparkgallery.data.gallery.model.Image
import com.challenge.sparkgallery.data.gallery.network.GalleryAPI
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.time.Instant
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by 805640 on 04.02.2018.
 */
@Singleton
class GalleryRepo @Inject constructor(private val imagesApiSource: GalleryAPI) {

    val firebaseImageStorage = FirebaseImageStorage()
    val firebaseGalleryDB = FirebaseCloudGalleryDb()

    //Commented out code was from my attempt at a PHP server
    fun getImages(authorId: String, page: Int): Flowable<List<Image>> {
//        return imagesApiSource.getImages(page).map(GetImagesResponse::images)
        return firebaseGalleryDB.getImages(authorId, page)
    }

    fun uploadImage(authorId: String, path: String, title: String) : Flowable<Image> {
        return firebaseImageStorage.uploadFile(File(path))
                .map { it.toString() }
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .flatMap { firebaseGalleryDB.addImageToGallery(Image("", authorId, it, title, Date())) }
    }
}