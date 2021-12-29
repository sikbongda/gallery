package com.wally.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wally.gallery.databinding.PhotoItemBinding
import com.wally.network.response.Photo

class PhotoAdapter(
    private val items: List<Photo>,
) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    inner class PhotoViewHolder(
        private val binding: PhotoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {

        }

        fun bind(photo: Photo) {
            binding.item = photo
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val v = PhotoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PhotoViewHolder(v)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}