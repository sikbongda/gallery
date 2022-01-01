package com.wally.gallery.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.wally.gallery.R

private const val WIDTH = 480
private const val HEIGHT = 240

@BindingAdapter("imageFromUrl")
fun setImageResource(iv: ImageView, id: String) {
    // "https://picsum.photos/id/1041/5184/2916" -> "https://picsum.photos/id/1041/512/128"
    Glide.with(iv.context)
        .load("https://picsum.photos/id/$id/$WIDTH/$HEIGHT")
        .placeholder(R.drawable.ic_launcher_foreground) // TODO: 적절한 이미지 필요
        //.apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
        .into(iv)
}