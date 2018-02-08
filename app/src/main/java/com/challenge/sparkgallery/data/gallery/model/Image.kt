package com.challenge.sparkgallery.data.gallery.model

import java.util.*

/**
 * Created by 805640 on 04.02.2018.
 */
data class Image(var id: String = "",
                 var authorId: String = "",
                 var imageUrl: String = "",
                 var title: String = "",
                 var uploadedAt: Date = Date())