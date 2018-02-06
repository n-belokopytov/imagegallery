package com.challenge.sparkgallery.android.features.gallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.challenge.sparkgallery.R
import com.challenge.sparkgallery.android.CIAApplication
import com.challenge.sparkgallery.data.gallery.model.Image
import com.challenge.sparkgallery.android.common.BaseActivity
import com.challenge.sparkgallery.android.common.di.DaggerUIComponent
import javax.inject.Inject

/**
 * Created by 805640 on 04.02.2018.
 */
class ImageGalleryActivity: BaseActivity(), ImageGalleryView {

    @Inject
    lateinit var imageGalleryPresenter: ImageGalleryPresenter

    override fun getLayoutID(): Int = R.layout.gallery_activity
    override fun getTitleID(): Int = R.string.gallery_title

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerUIComponent.builder()
                .appComponent(CIAApplication.applicationComponent)
                .build()
                .inject(this)



        imageGalleryPresenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        imageGalleryPresenter.detachView()
    }

    override fun onStart() {
        super.onStart()
        refresh()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addImagesToAdapter(images: List<Image>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, this::class.java)
        }
    }

    override fun loadGalleryPage(page: Int) {
        imageGalleryPresenter.loadGalleryPage(page)
    }

    override fun refresh() {
        imageGalleryPresenter.refresh()
    }

    override fun addNewImage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}