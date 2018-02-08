package com.challenge.sparkgallery.data.gallery.firebase

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.Observable
import java.io.InputStream
import com.google.firebase.storage.UploadTask
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.OnFailureListener
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.internal.operators.flowable.FlowableFromCallable
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileInputStream


/**
 * Created by 805640 on 07.02.2018.
 */
class FirebaseImageStorage {

    var storage = FirebaseStorage.getInstance()
    var imageRef = storage.reference.child("images")

    private val dataEmitter: PublishProcessor<Uri> = PublishProcessor.create()

    fun uploadFile(file : File) : Flowable<Uri> {
        val uploadTask = imageRef.child(file.name).putStream(FileInputStream(file) as InputStream)
        uploadTask.addOnFailureListener({
            dataEmitter.onError(it)
        }).addOnSuccessListener({ taskSnapshot ->
            val downloadUrl = taskSnapshot.downloadUrl
            dataEmitter.onNext(downloadUrl)
        })
        return dataEmitter.subscribeOn(Schedulers.io())
    }
}