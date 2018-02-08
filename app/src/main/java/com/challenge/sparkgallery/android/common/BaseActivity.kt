package com.challenge.sparkgallery.android.common

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric





/**
 * Created by 805640 on 04.02.2018.
 */
abstract class BaseActivity : AppCompatActivity() {

    lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Fabric.with(this, Crashlytics())
        setContentView(getLayoutID())
        setTitle(getTitleID())
    }

    @StringRes
    abstract fun getTitleID(): Int

    @LayoutRes
    abstract fun getLayoutID(): Int
}