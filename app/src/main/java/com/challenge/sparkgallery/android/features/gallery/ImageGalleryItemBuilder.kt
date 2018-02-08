package com.challenge.sparkgallery.android.features.gallery

import com.challenge.sparkgallery.data.gallery.model.Image
import java.time.LocalDate
import java.util.*

/**
 * Created by 805640 on 06.02.2018.
 */
class ImageGalleryItemBuilder(var images : List<Image>) {
    fun build() : List<Any> {
        val result = ArrayList<Any>()
        var lastAddedDate = Date()
        result.add(lastAddedDate)
        for (image : Image in images) {
            if (lastAddedDate.date != image.uploadedAt.date) {
                lastAddedDate = image.uploadedAt
                result.add(image.uploadedAt)
            }
            result.add(image)
        }
        return result
    }
}