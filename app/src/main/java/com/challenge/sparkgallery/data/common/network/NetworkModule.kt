package com.challenge.sparkgallery.data.common.network

import com.challenge.sparkgallery.data.user.UserRepo
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor

/**
 * Created by 805640 on 06.02.2018.
 */

@Module
class NetworkModule {

    @Provides
    fun providesCallInterceptor(userRepo: UserRepo): Interceptor {
        //here be build-flavor dependent code
        return CYAInterceptor(CYAConfig(), userRepo)
    }
}