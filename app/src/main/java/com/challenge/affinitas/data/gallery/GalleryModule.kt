package com.challenge.affinitas.data.gallery

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by 805640 on 04.02.2018.
 */
@Module
class GalleryModule {

    @Provides
    @Singleton
    fun providesGalleryAPI(): GalleryAPI {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build();
        return Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GalleryAPI::class.java)
    }
}