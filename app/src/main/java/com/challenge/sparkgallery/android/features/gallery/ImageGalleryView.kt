package com.challenge.sparkgallery.android.features.gallery

import com.challenge.sparkgallery.data.gallery.model.Image
import com.challenge.sparkgallery.android.common.BaseView

/**
 * Created by 805640 on 04.02.2018.
 */
interface ImageGalleryView: BaseView {
    fun refresh()
    fun loadGalleryPage(page: Int)
    fun addNewImage()
    fun addImages(images: List<Image>)
    fun showList()
    fun showEmpty()
    fun login()
}