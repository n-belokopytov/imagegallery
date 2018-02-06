package com.challenge.sparkgallery.android.common

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity

/**
 * Created by 805640 on 04.02.2018.
 */
abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
        setTitle(getTitleID())
    }

    @StringRes
    abstract fun getTitleID(): Int

    @LayoutRes
    abstract fun getLayoutID(): Int
}