package com.challenge.sparkgallery.data.common.network

/**
 * Created by 805640 on 06.02.2018.
 */
interface NetworkConfig {
    fun getVersion() : String
    fun getToken() : String
    fun getBaseUrl() : String
    fun getAgent() : String

}