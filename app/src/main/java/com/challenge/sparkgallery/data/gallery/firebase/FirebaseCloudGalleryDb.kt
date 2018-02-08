package com.challenge.sparkgallery.data.gallery.firebase

import android.net.Uri
import com.challenge.sparkgallery.data.gallery.model.Image
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers


/**
 * Created by 805640 on 08.02.2018.
 */
class FirebaseCloudGalleryDb {

    var IMAGES_COLLECTION = "gallery_images"
    var IMAGE_LIMIT : Long = 10

    var db = FirebaseFirestore.getInstance()

    private val addDataEmitter: PublishProcessor<Image> = PublishProcessor.create()
    private val queryDataEmitter: PublishProcessor<List<Image>> = PublishProcessor.create()


    fun addImageToGallery(image : Image) : Flowable<Image> {
        val doc = db.collection(IMAGES_COLLECTION).document()
        image.id = doc.id
        db.collection(IMAGES_COLLECTION).add(image)
                .addOnSuccessListener {
                    addDataEmitter.onNext(image)
                }
                .addOnFailureListener {
                    addDataEmitter.onError(it)
                }
        return addDataEmitter.subscribeOn(Schedulers.io())
    }

    fun getImages(authorId : String, page : Int) : Flowable<List<Image>> {
        db.collection(IMAGES_COLLECTION)
                .whereEqualTo(Image::authorId.name, authorId)
                .orderBy(Image::uploadedAt.name, Query.Direction.DESCENDING)
//                .startAt(page * IMAGE_LIMIT)
//                .limit(IMAGE_LIMIT)
                .get()
                .addOnSuccessListener {
                    queryDataEmitter.onNext(it.toObjects(Image::class.java))
                }
                .addOnFailureListener {
                    queryDataEmitter.onError(it)
                }

        return queryDataEmitter.subscribeOn(Schedulers.io())
    }
}