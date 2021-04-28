package com.example.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.Adapter.MainAdapter
import com.example.retrofit.Model.MainModel
import com.example.retrofit.Retrofit.ApiService
import com.example.retrofit.Retrofit.Listresponnse
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
            getUserFromAPI()
            moveToCreateActivity()
        }

        private fun moveToCreateActivity() {
            binding.fab.setOnClickListener {
                startActivity(Intent(this, CreateUpdateActivity::class.java))
            }
        }

        private fun setupRecycler( user : List<MainModel>){
            mainAdapter = MainAdapter(user, object : MainAdapter.ViewClickListener{
                override fun onViewClickListener(user: MainModel) {
                    startActivity(Intent(this@MainActivity, DetailActivity::class.java).apply {
                        putExtra("id", user.id)
                    })
                }

                override fun onUpdate(user: MainModel) {
                    startActivity(Intent(this@MainActivity, CreateUpdateActivity::class.java).apply {
                        putExtra("id", user.id)
                        putExtra("avatar", user.avatar)
                        putExtra("email", user.email)
                        putExtra("fname", user.first_name)
                        putExtra("lname", user.last_name)
                    })
                }
            })

            binding.rvData.apply {
                adapter = mainAdapter
                val mLayoutManager = LinearLayoutManager(this@MainActivity)
                mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
                layoutManager = mLayoutManager
            }
        }


        private fun getUserFromAPI(){
            ApiService.endpoint.getUser().enqueue(object : Callback<Listresponnse<MainModel>>{
                override fun onFailure(call: Call<Listresponnse<MainModel>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Cannot connect to server", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Listresponnse<MainModel>>, response: Response<Listresponnse<MainModel>>) {
                    if(response.isSuccessful){
                        val body = response.body()
                        if (body != null){
                            Log.d("users", "onResponse: $body")
                            setupRecycler(body.data)
                        }

                    }
                }
            })
        }
}