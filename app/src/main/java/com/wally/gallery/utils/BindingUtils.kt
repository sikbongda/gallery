package com.wally.gallery.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.placeholderOf
import com.wally.gallery.R

@BindingAdapter("imageFromUrl")
fun setImageResource(iv: ImageView, url: String) {
    Glide.with(iv.context)
        .load(url)
        .apply(placeholderOf(R.drawable.ic_launcher_foreground)) // TODO: 적절한 이미지 필요
        .into(iv)
}