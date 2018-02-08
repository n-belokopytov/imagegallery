package com.challenge.sparkgallery.android.features.auth

import com.challenge.sparkgallery.android.common.BasePresenter
import com.challenge.sparkgallery.android.common.di.UIScope
import com.challenge.sparkgallery.data.user.UserRepo
import com.challenge.sparkgallery.data.user.model.User
import javax.inject.Inject

/**
 * Created by 805640 on 07.02.2018.
 */
@UIScope
class AuthPresenter @Inject constructor(private var userRepo : UserRepo) : BasePresenter<AuthView>() {

    fun init() {
        if (userRepo.user == null) {
            view?.showAuthForm()
        }
    }

    fun onLoginWithUser(user : User) {
        userRepo.user = user
        view?.finishAuth()
    }

    fun onAuthError(errorCode: Int?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}