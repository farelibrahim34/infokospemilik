package com.projectfarrel.infokosadmin.network


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


object ApiClient {
    const val BASE_URL = "https://641853be29e7e36438e58c8c.mockapi.io/"
    const val BASE_URL_DUA = "https://65648fa2ceac41c0761e6d31.mockapi.io/"


    val instance :ApiInterface by lazy {
        val retrofit =Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiInterface::class.java)
    }
    val instanceDua :ApiInterface by lazy {
        val retrofit =Retrofit.Builder()
            .baseUrl(BASE_URL_DUA)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiInterface::class.java)
    }


}