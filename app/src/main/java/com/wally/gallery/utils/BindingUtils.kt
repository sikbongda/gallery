package com.wally.gallery.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.wally.gallery.R

private const val WIDTH = 480
private const val HEIGHT = 240

@BindingAdapter("imageFromUrl")
fun setImageResource(iv: ImageView, id: String) {
    Glide.with(iv.context)
        .load("https://picsum.photos/id/$id/$WIDTH/$HEIGHT")
        .placeholder(R.drawable.ic_launcher_foreground) // TODO: 적절한 이미지 필요
        .into(iv)
}

@BindingAdapter("bookmarked")
fun setBookmark(iv: ImageView, bookmarked: Boolean) {
    if (bookmarked) {
        iv.setImageResource(R.drawable.ic_baseline_star_24)
    } else {
        iv.setImageResource(R.drawable.ic_baseline_star_border_24)
    }
}