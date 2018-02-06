package com.challenge.sparkgallery.data.gallery

import com.challenge.sparkgallery.data.gallery.model.GetImagesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

/**
 * Created by 805640 on 04.02.2018.
 */
@Singleton
interface GalleryAPI {

    @GET
    fun getImages(@Query("page")page: Int): Observable<GetImagesResponse>
}