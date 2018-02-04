package com.challenge.affinitas.android.common

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by 805640 on 04.02.2018.
 */
abstract class BasePresenter<MVPView: BaseView> {
    var disposable: CompositeDisposable? = null
    protected var view: MVPView? = null

    fun attachView(view: MVPView) {
        this.view = view
    }

    fun detachView() {
        view = null
        if (disposable?.isDisposed == false) {
            disposable?.dispose()
        }
    }
}