package com.example.retrofit.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.retrofit.Model.MainModel
import com.example.retrofit.databinding.ItemDataBinding

class MainAdapter(private val users : List<MainModel>
                  , val listener : MainAdapter.ViewClickListener)
    : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemDataBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemDataBinding.inflate(LayoutInflater.from(parent.context)
                , parent, false))
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            TVName.text = users[position].first_name
            ImageUser.load(users[position].avatar)
        }
        holder.binding.ImageUser.setOnClickListener {
            listener.onViewClickListener(users[position])
        }
        holder.binding.ImgUpdate.setOnClickListener {
            listener.onUpdate(users[position])
        }


    }
    interface ViewClickListener{
        fun onViewClickListener(user : MainModel)
        fun onUpdate(user : MainModel)
    }

}