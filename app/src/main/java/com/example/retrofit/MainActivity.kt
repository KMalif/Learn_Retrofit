    package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.Adapter.MainAdapter
import com.example.retrofit.Model.MainModel
import com.example.retrofit.Retrofit.ApiService
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
    class MainActivity : AppCompatActivity() {

        lateinit var mainAdapter: MainAdapter
        private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()
        setupRecycler()
        getDataFromAPI()
    }

    private fun setupRecycler(){
        mainAdapter = MainAdapter(arrayListOf())
        binding.rvData.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mainAdapter
        }
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
        val result = photos
        mainAdapter.setupData(result)

    }

}