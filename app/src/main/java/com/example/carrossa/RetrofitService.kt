package com.example.carrossa

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    //pour instancier interface enpoint

    val endpoint = Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(Endpoint::class.java)
}