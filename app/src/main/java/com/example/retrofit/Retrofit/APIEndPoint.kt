package com.example.retrofit.Retrofit

import com.example.retrofit.Model.MainModel
import retrofit2.Call
import retrofit2.http.GET


interface APIEndPoint {
    @GET("photos")
    fun getPhotos(): Call<List<MainModel>>

}