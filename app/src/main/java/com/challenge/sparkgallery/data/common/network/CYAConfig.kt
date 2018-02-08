package com.challenge.sparkgallery.data.common.network

import com.challenge.sparkgallery.BuildConfig

/**
 * Created by 805640 on 06.02.2018.
 */
class CYAConfig : NetworkConfig {
    override fun getVersion() : String {
        return VERSION
    }

    override fun getToken() : String {
        return API_TOKEN
    }

    override fun getBaseUrl() : String {
        return BASE_URL
    }

    override fun getAgent(): String {
        return AGENT
    }

    companion object {
        private val API_TOKEN: String = "whowhowho"
        private val BASE_URL: String = "https://127.0.0.1"
        private val VERSION: String = BuildConfig.VERSION_NAME
        private val AGENT: String = "Android-CYA-gallery"
    }
}