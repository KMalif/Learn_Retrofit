package com.example.retrofit.Retrofit

import com.example.retrofit.Model.MainModel
import retrofit2.Call
import retrofit2.http.GET


interface APIEndPoint {
    @GET("data.php")
    fun getPhotos(): Call<MainModel>

}