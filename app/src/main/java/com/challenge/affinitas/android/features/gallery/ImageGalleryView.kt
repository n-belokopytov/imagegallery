package com.challenge.affinitas.android.features.gallery

import com.challenge.affinitas.data.gallery.model.Image
import com.challenge.affinitas.android.common.BaseView

/**
 * Created by 805640 on 04.02.2018.
 */
interface ImageGalleryView: BaseView {
    fun refresh()
    fun loadGalleryPage(page: Int)
    fun addNewImage()
    fun addImagesToAdapter(images: List<Image>)
}