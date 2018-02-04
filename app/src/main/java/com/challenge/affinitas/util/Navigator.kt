package com.challenge.affinitas.util

import android.content.Context
import com.challenge.affinitas.android.features.gallery.ImageGalleryActivity

/**
 * Created by 805640 on 04.02.2018.
 */
class Navigator {
    companion object {
        fun invokeGallery(context: Context) {
            context.startActivity(ImageGalleryActivity.getIntent(context))
        }
    }
}