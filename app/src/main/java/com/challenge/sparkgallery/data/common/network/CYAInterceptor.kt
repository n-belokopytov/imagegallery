package com.challenge.sparkgallery.data.common.network

import com.challenge.sparkgallery.data.user.UserRepo
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by 805640 on 06.02.2018.
 */
class CYAInterceptor (val config : NetworkConfig, val userRepo : UserRepo) : Interceptor {

    private val HEADER_TOKEN = "X-TOKEN"
    private val HEADER_VERSION = "X-VERSION"
    private val HEADER_USER_AGENT = "User-Agent"
    private val HEADER_VISITOR_ID = "X-VISITOR-ID"


    override fun intercept(chain: Interceptor.Chain): Response {
        var headers = chain.request().headers()
        headers = headers.newBuilder()
                    .add(HEADER_VISITOR_ID, userRepo.visitorId)
                    .add(HEADER_VERSION, config.getVersion())
                    .add(HEADER_TOKEN, config.getToken())
                    .add(HEADER_USER_AGENT, config.getAgent())
                    .build()
        return chain.proceed(chain.request().newBuilder().headers(headers).build())
    }
}