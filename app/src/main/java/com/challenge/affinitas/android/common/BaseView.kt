package com.challenge.affinitas.android.common

/**
 * Created by 805640 on 04.02.2018.
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(throwable: Throwable)
}