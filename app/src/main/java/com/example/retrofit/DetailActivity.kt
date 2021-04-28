package com.example.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.load
import com.example.retrofit.Model.MainModel
import com.example.retrofit.Retrofit.ApiService
import com.example.retrofit.Retrofit.singleResponse
import com.example.retrofit.databinding.ActivityDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getUserById()
        btnDeleteAction()
    }

    private fun getUserById(){
        ApiService.endpoint.getUserById(intent.getIntExtra("id", 1))
                .enqueue(object : Callback<singleResponse<MainModel>>{
            override fun onFailure(call: Call<singleResponse<MainModel>>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<singleResponse<MainModel>>, response: Response<singleResponse<MainModel>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        viewData(body.data)
                    }
                }
            }
        })
    }

    private fun viewData(data : MainModel){
        if (data != null){
            binding.tvName.text = "${data.first_name} ${data.last_name}"
            binding.TvEmail.text = "${data.email}"
            binding.imgDetail.load(data.avatar)
        }
    }
    private fun btnDeleteAction(){
        binding.BtnDelete.setOnClickListener {
            deleteUser()
            finish()
        }
    }

    private fun deleteUser(){
        ApiService.endpoint.deleteUser(intent.getIntExtra("id", 0))
                .enqueue(object : Callback<Void>{
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        println(t.message)
                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful){
                            Toast.makeText(applicationContext, response.code().toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                })
    }

}