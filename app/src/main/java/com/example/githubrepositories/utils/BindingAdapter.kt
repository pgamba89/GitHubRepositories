package com.example.githubrepositories.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.githubrepositories.model.Owner

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, owner: Owner) {
    owner.let {
        val imgUrl = owner.avatar_url
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}