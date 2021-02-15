     package com.example.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
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
        mainAdapter = MainAdapter(arrayListOf(), object : MainAdapter.ViewClickListener{
            override fun onViewClickListener(result: MainModel.Result) {
                Toast.makeText(applicationContext, result.title, Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, DetailActivity::class.java)
                    .putExtra("title", result.title)
                    .putExtra("image", result.image)
                )
            }
        })
        binding.rvData.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mainAdapter
        }
    }
    private fun getDataFromAPI(){
        binding.ProgressBar.visibility = View.VISIBLE
        ApiService.endpoint.getPhotos()
                .enqueue(object : Callback<MainModel>{
                    override fun onFailure(call: Call<MainModel>, t: Throwable) {
                        binding.ProgressBar.visibility = View.GONE
                        log( t.toString())
                    }

                    override fun onResponse(call: Call<MainModel>, response: Response<MainModel>) {
                        binding.ProgressBar.visibility = View.GONE
                        if (response.isSuccessful){
                                showData(response.body()!!)
                            }
                        }
                })

    }

    private fun log(message : String){
        val TAG = "MainActivity"
        Log.d(TAG, message)

    }
    private fun showData(data : MainModel){
        val results = data.result
        mainAdapter.setupData(results)

    }

}