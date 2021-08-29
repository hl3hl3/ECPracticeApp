package com.hl3hl3.ecpracticeapp

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hl3hl3.ecpracticeapp.vo.Banner


@BindingAdapter("homeBannerList")
fun bindRecyclerViewWithDataItemList(recyclerView: RecyclerView, bannerList: List<Banner>?) {
    bannerList?.let {
        Logger.logD("Binding", "bindRecyclerViewWithDataItemList()")
        recyclerView.adapter?.apply {
            when (this) {
                is BannerAdapter -> submitList(it)
            }
        }
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, url: String?) {
    val context: Context = imageView.getContext()
    Glide.with(context)
        .load(url)
        .into(imageView)
}