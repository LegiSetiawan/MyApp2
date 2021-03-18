package com.udah.myapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    fun getRetrofit():Retrofit {
        return Retrofit.Builder().baseUrl("http://192.168.43.146/serverapi/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun service():AppService = getRetrofit().create(AppService::class.java)
}