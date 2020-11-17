package com.example.androidpractices.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orendaandroidtask.R
import com.example.androidpractices.model.PhotoDetail
import com.squareup.picasso.Picasso


class PhotosAdapter(val photoDetailsList : MutableList<PhotoDetail>, var context : Context) : RecyclerView.Adapter<PhotosAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosAdapterViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.photos_adapter_layout, parent, false)
        return PhotosAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photoDetailsList.size
    }

    override fun onBindViewHolder(holder: PhotosAdapterViewHolder, position: Int) {

        val item = photoDetailsList.get(position)

        if (item.thumbnailUrl != null || item.thumbnailUrl != "") {
            Picasso.get().load(item.thumbnailUrl).into(holder.itemView.findViewById<ImageView>(R.id.thumbnail_id))
        }

        if (item.title != null || item.title != "") {
            holder.itemView.findViewById<TextView>(R.id.tv_title).text = item.title
        }

        if (item.url != null || item.url != "") {
            Picasso.get().load(item.url).into(holder.itemView.findViewById<ImageView>(R.id.image_id))
        }

    }


}

class PhotosAdapterViewHolder(view : View) : RecyclerView.ViewHolder(view)
