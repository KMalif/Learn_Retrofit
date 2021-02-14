package com.example.retrofit.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
        //https://jsonplaceholder.typicode.com/
    val BASE_URL:String = "https://demo.lazday.com/rest-api-sample/"
    val endpoint  : APIEndPoint

    get() {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

        return retrofit.create(APIEndPoint::class.java)
    }
}