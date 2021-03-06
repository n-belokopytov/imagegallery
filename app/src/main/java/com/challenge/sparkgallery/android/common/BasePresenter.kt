package com.challenge.sparkgallery.android.common

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by 805640 on 04.02.2018.
 */
abstract class BasePresenter<MVPView: BaseView> {
    var disposable: CompositeDisposable? = CompositeDisposable()
    protected var view: MVPView? = null

    fun attachView(view: MVPView) {
        this.view = view
    }

    fun detachView() {
        view = null
        disposable?.clear()
    }
}