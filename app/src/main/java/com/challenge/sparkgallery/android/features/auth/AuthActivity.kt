package com.challenge.sparkgallery.android.features.auth

import android.content.Intent
import android.os.Bundle
import com.challenge.sparkgallery.android.common.BaseActivity
import com.firebase.ui.auth.AuthUI
import java.util.*
import com.google.firebase.auth.FirebaseAuth
import android.app.Activity
import android.content.Context
import com.challenge.sparkgallery.R
import com.challenge.sparkgallery.android.CYAApplication
import com.challenge.sparkgallery.android.common.di.DaggerUIComponent
import com.challenge.sparkgallery.data.user.firebase.UserFirebaseStorage
import com.firebase.ui.auth.IdpResponse
import javax.inject.Inject


/**
 * Created by 805640 on 07.02.2018.
 */
class AuthActivity : BaseActivity(), AuthView {

    private val RC_SIGN_IN = 123

    @Inject
    lateinit var varAuthPresenter : AuthPresenter

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, AuthActivity::class.java)
        }
    }

    override fun getTitleID(): Int {
        return R.string.auth_title
    }

    override fun getLayoutID(): Int {
        return R.layout.auth_activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerUIComponent.builder()
                .appComponent(CYAApplication.applicationComponent)
                .build()
                .inject(this)
        varAuthPresenter.attachView(this)
    }

    override fun onStart() {
        super.onStart()
        varAuthPresenter.init()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun finishAuth() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun showAuthForm() {
        val providers = Arrays.asList(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build())
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setLogo(R.drawable.ic_account_box)
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                user?.let {
                    varAuthPresenter.onLoginWithUser(UserFirebaseStorage.mapUser(user))
                }
            } else {
                varAuthPresenter.onAuthError(response?.errorCode)
            }
        }
    }
}