package com.challenge.sparkgallery.android.features.auth

import com.challenge.sparkgallery.android.common.BaseView

/**
 * Created by 805640 on 07.02.2018.
 */
interface AuthView : BaseView {
    fun showAuthForm()
    fun finishAuth()
}