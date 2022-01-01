package com.wally.gallery.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wally.gallery.databinding.PhotoItemBinding
import com.wally.network.response.Photo

class PhotoPagingDataAdapter(
    val clickListener: (photo: Photo) -> Unit
) : PagingDataAdapter<Photo, PhotoPagingDataAdapter.PhotoViewHolder>(COMPARATOR) {
    inner class PhotoViewHolder(
        private val binding: PhotoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                binding.item?.let {
                    clickListener.invoke(it)
                }
            }
        }

        fun bind(photo: Photo?) {
            ViewCompat.setTransitionName(binding.imagePhoto, "image_${photo?.id}")
            with(binding) {
                item = photo
                executePendingBindings()
            }
        }
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem == newItem
        }
    }

}