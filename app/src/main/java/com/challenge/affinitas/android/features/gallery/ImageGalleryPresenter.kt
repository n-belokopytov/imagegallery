package com.challenge.affinitas.android.features.gallery

import com.challenge.affinitas.data.gallery.GalleryRepo
import com.challenge.affinitas.data.gallery.model.Image
import com.challenge.affinitas.android.common.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by 805640 on 04.02.2018.
 */
class ImageGalleryPresenter : BasePresenter<ImageGalleryView>() {

    @Inject
    lateinit var imageRepo: GalleryRepo

    fun loadGalleryPage(page: Int) {
        disposable?.add(imageRepo.getImages(page)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({ addImagesToAdapter(it) },
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