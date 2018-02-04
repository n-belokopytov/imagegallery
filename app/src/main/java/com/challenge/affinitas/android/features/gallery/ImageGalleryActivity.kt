package com.challenge.affinitas.android.features.gallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.challenge.affinitas.data.gallery.model.Image
import com.challenge.affinitas.android.common.BaseActivity

/**
 * Created by 805640 on 04.02.2018.
 */
class ImageGalleryActivity: BaseActivity(), ImageGalleryView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addNewImage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}