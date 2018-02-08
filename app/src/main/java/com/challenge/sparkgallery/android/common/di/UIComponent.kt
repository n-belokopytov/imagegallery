package com.challenge.sparkgallery.android.common.di

import com.challenge.sparkgallery.android.AppComponent
import com.challenge.sparkgallery.android.features.auth.AuthActivity
import com.challenge.sparkgallery.android.features.auth.AuthPresenter
import com.challenge.sparkgallery.android.features.gallery.ImageGalleryActivity
import com.challenge.sparkgallery.android.features.gallery.ImageGalleryPresenter
import dagger.Component

/**
 * Created by 805640 on 06.02.2018.
 */
@UIScope
@Component(dependencies = [AppComponent::class])
interface UIComponent {

    fun inject(imageGalleryActivity: ImageGalleryActivity)
    fun inject(authActivity: AuthActivity)

    @UIScope
    fun authPresenter(): AuthPresenter

    @UIScope
    fun imageGalleryPresenter(): ImageGalleryPresenter
}