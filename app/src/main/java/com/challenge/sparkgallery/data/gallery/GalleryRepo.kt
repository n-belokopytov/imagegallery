package com.challenge.sparkgallery.data.gallery

import com.challenge.sparkgallery.data.gallery.model.GetImagesResponse
import com.challenge.sparkgallery.data.gallery.model.Image
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by 805640 on 04.02.2018.
 */
@Singleton
class GalleryRepo @Inject constructor(private val imagesApiSource: GalleryAPI) {

    fun getImages(page: Int): Observable<List<Image>> {
        return imagesApiSource.getImages(page).map(GetImagesResponse::images)
    }
}