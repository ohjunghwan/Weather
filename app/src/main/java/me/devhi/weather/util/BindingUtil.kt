package me.devhi.weather.util

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition


@BindingAdapter("app:image_url")
fun setImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .centerCrop()
        .into(view)
}

@BindingAdapter("app:background_url")
fun setBackground(view: View, url: String) {
    Glide.with(view.context)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                a_resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                view.background = BitmapDrawable(view.context.resources, a_resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
}