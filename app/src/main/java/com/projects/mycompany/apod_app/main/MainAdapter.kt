package com.projects.mycompany.apod_app.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projects.mycompany.apod_app.R
import com.projects.mycompany.apod_app.data.Apod


class MainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    var apodList = listOf<Apod>()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.apod_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return apodList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = apodList[position]
        holder.bind(item)
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val apodImage = itemView.findViewById<ImageView>(R.id.apod_image)

        fun bind(apod: Apod){
            val uri = apod.url.toUri().buildUpon().scheme("https").build()
            Glide.with(apodImage.context)
                .load(uri)
                .into(apodImage)
        }
    }

}