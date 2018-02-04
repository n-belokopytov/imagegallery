package com.challenge.affinitas.android.common

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity

/**
 * Created by 805640 on 04.02.2018.
 */
@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity() {
    override fun onStart() {
        super.onStart()
        //track view
    }
}