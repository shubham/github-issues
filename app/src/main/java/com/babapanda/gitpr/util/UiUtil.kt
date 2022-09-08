package com.babapanda.gitpr.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.babapanda.gitpr.R
import com.bumptech.glide.Glide

object UiUtil {
    @JvmStatic
    @BindingAdapter("imageUrl", "placeholder")
    fun setImage(image: ImageView, url: String?, placeHolder: Drawable) {
        if (!url.isNullOrEmpty()) {
            Glide.with(image.context).load(url).centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(image)
        } else {
            image.setImageDrawable(placeHolder)
        }
    }
}