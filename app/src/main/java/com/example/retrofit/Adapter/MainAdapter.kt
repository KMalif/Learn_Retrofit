package com.example.retrofit.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.Model.MainModel
import com.example.retrofit.databinding.ItemDataBinding

class MainAdapter(val result : ArrayList<MainModel>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemDataBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return result.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val res = result[position]
        holder.binding.apply {
            tvPhotos.text = result[position].title
            Glide.with(holder.itemView).load(res.url).into(holder.binding.imgPhotos)
        }

    }
    fun setupData(data : List<MainModel>){
        result.clear()
        result.addAll(data)
        notifyDataSetChanged()
    }

}