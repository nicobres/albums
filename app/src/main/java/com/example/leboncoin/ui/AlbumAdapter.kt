package com.example.leboncoin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.example.leboncoin.data.Album
import com.example.leboncoin.databinding.ViewAlbumBinding

class AlbumsAdapter(var albums: List<Album>) : RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ViewAlbumBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(ViewAlbumBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val url = GlideUrl(
            albums[position].thumbnailUrl,
            { mapOf("User-Agent" to "1") }
        )
        Glide
            .with(holder.binding.root)
            .load(url)
            .into(holder.binding.albumThumbnail)

        holder.binding.albumDescription.text = albums[position].title
    }

    override fun getItemCount() = albums.size
}