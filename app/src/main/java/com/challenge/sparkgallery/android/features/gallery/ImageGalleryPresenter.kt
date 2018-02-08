package com.challenge.sparkgallery.android.features.gallery

import com.challenge.sparkgallery.android.common.di.UIScope
import com.challenge.sparkgallery.data.gallery.GalleryRepo
import com.challenge.sparkgallery.data.gallery.model.Image
import com.challenge.sparkgallery.android.common.BasePresenter
import com.challenge.sparkgallery.data.user.UserRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by 805640 on 04.02.2018.
 */
@UIScope
class ImageGalleryPresenter @Inject constructor(private val galleryRepo: GalleryRepo, private val userRepo: UserRepo): BasePresenter<ImageGalleryView>() {

    private var images: MutableList<Image> = ArrayList()

    fun refresh() {
        if (userRepo.user != null) {
            images.clear()
            loadGalleryPage(0)
        } else {
            view?.login()
        }
    }

    fun loadGalleryPage(page: Int) {
        disposable?.clear()
        disposable?.add(galleryRepo.getImages(userRepo.user!!.userId, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe({
                    view?.showLoading()
                })
                .subscribe({
                    images.addAll(it)
                    addImagesToAdapter(it)
                    view?.hideLoading()
                    if (images.isNotEmpty()) {
                        view?.showList()
                    } else {
                        view?.showEmpty()
                    }
                },
                {
                    view?.hideLoading()
                    view?.showError(it)
                }))
    }

    fun onNewPictureClicked() {
        if (userRepo.user != null) {
            view?.addNewImage()
        } else {
            view?.login()
        }
    }

    fun uploadImage(path : String, name : String) {
        disposable?.clear()
        userRepo.user?.let {
            galleryRepo.uploadImage(it.userId, path, name).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe({
                        view?.showLoading()
                    })
                    .subscribe({
                        refresh()
                    },
                    {
                        view?.hideLoading()
                        view?.showError(it)
                    })
        }?.let { disposable?.add(it) }
    }

    fun addImagesToAdapter(images: List<Image>) {
        view?.addImages(images)
    }
}