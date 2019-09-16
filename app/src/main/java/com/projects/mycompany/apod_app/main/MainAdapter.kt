package com.projects.mycompany.apod_app.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.projects.mycompany.apod_app.data.Apod
import com.projects.mycompany.apod_app.databinding.ApodItemBinding


class MainAdapter(val onClickListener: OnClickListener): ListAdapter<Apod,MainAdapter.ViewHolder>(ApodDiffCallback()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
    }

    class ViewHolder private constructor(val binding: ApodItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(apod: Apod){
            binding.apod = apod
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ApodItemBinding.inflate(layoutInflater)
                return ViewHolder(binding)
            }
        }
    }

    class OnClickListener(val listener : (apod: Apod) -> Unit){
        fun onClick (apod: Apod) = listener(apod)
    }

    class ApodDiffCallback: DiffUtil.ItemCallback<Apod>(){
        override fun areItemsTheSame(oldItem: Apod, newItem: Apod): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Apod, newItem: Apod): Boolean {
            return oldItem == newItem
        }

    }
}