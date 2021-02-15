package com.example.retrofit.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.Model.MainModel
import com.example.retrofit.databinding.ItemDataBinding

class MainAdapter(val result : ArrayList<MainModel.Result>, val listener : MainAdapter.ViewClickListener): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemDataBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return result.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            tvPhotos.text = result[position].title
        }
        Glide.with(holder.itemView)
                .load(result[position].image)
                .centerCrop()
                .into(holder.binding.imgPhotos)
        holder.itemView.setOnClickListener {
            listener.onViewClickListener(result[position])
        }

    }
    fun setupData(data : List<MainModel.Result>){
        result.clear()
        result.addAll(data)
        notifyDataSetChanged()
    }
    interface ViewClickListener{
        fun onViewClickListener(result : MainModel.Result)
    }

}