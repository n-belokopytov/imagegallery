package com.challenge.sparkgallery.android.features.gallery

import com.challenge.sparkgallery.android.common.di.UIScope
import com.challenge.sparkgallery.data.gallery.GalleryRepo
import com.challenge.sparkgallery.data.gallery.model.Image
import com.challenge.sparkgallery.android.common.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by 805640 on 04.02.2018.
 */
@UIScope
class ImageGalleryPresenter @Inject constructor(private val galleryRepo: GalleryRepo): BasePresenter<ImageGalleryView>() {

    private var images: MutableList<Image> = ArrayList()

    fun refresh() {
        images.clear()
        loadGalleryPage(0)
    }

    fun loadGalleryPage(page: Int) {
        disposable?.add(galleryRepo.getImages(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    images.addAll(it)
                    addImagesToAdapter(it)
                },
                {
                    view?.showError(it)
                }))
    }

    fun addNewPicture() {

    }

    fun addImagesToAdapter(images: List<Image>) {
        view?.addImagesToAdapter(images)
    }
}