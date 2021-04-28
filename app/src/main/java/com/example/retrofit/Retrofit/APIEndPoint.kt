package com.example.retrofit.Retrofit

import com.example.retrofit.Model.MainModel
import com.example.retrofit.Model.books
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path


interface APIEndPoint {
    @GET("api/users")
    fun getUser () : Call<Listresponnse<MainModel>>

    @GET("api/users/{id}")
    fun getUserById(@Path("id")id : Int): Call<singleResponse<MainModel>>

    @DELETE("api/users/{id}")
    fun deleteUser(@Path("id")id : Int):Call<Void>
}


data class Listresponnse<T>(
        var page : Int,
        var per_page : Int,
        var total : Int,
        var total_pages : Int,
        var data : List<T>
)
data class singleResponse<T>(
        var page : Int,
        var per_page : Int,
        var total : Int,
        var total_pages : Int,
        var data : T
)