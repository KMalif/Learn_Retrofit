    package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofit.Model.MainModel
import com.example.retrofit.Retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

    class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
        getDataFromAPI()
    }

    private fun getDataFromAPI(){

        ApiService.endpoint.getPhotos()
                .enqueue(object : Callback<List<MainModel>>{
                    override fun onFailure(call: Call<List<MainModel>>, t: Throwable) {
                        log( t.toString())
                    }

                    override fun onResponse(call: Call<List<MainModel>>, response: Response<List<MainModel>>) {

                        if (response.isSuccessful){
                            val result = response.body()
                            if (result != null) {
                                showPhotos(result)
                            }
                        }
                    }

                })

    }

    private fun log(message : String){
        val TAG = "MainActivity"
        Log.d(TAG, message)

    }
    private fun showPhotos(photos : List<MainModel>){
        for (photo in photos){
            log("title : ${photo.title}")
        }
    }

}