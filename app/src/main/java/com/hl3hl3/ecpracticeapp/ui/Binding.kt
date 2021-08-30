package com.hl3hl3.ecpracticeapp

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hl3hl3.ecpracticeapp.ui.qrcodeinfo.GZxingEncoder
import com.hl3hl3.ecpracticeapp.vo.Banner
import com.hl3hl3.ecpracticeapp.vo.Message


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

@BindingAdapter("messageList")
fun bindMessageRecyclerViewWithItemList(recyclerView: RecyclerView, list: List<Message>?) {
    list?.let {
        Logger.logD("Binding", "bindMessageList()")
        recyclerView.adapter?.apply {
            when (this) {
                is MessageAdapter -> submitList(it)
            }
        }
    }
}

@BindingAdapter("isEditMode")
fun bindMessageListEditMode(recyclerView: RecyclerView, isEditMode: Boolean?) {
    recyclerView.adapter?.apply {
        if (this is MessageAdapter) {
            setEditMode(isEditMode ?: false)
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

@BindingAdapter("qrcodeContent")
fun bindQrcode(imageView: ImageView, content: String?) {
    if (content.isNullOrBlank()) {
        // TODO 可顯示預設圖
    } else {
        imageView.setImageBitmap(GZxingEncoder.generateQRCode_general(content))
    }
}

@BindingAdapter("isShow")
fun bindIsShow(textView: TextView, isShow: Boolean?) {
    textView.isVisible = isShow == true
}
