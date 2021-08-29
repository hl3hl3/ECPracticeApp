package com.hl3hl3.ecpracticeapp

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hl3hl3.ecpracticeapp.databinding.HomeBannerItemBinding
import com.hl3hl3.ecpracticeapp.vo.Banner

class BannerAdapter : ListAdapter<Banner, RecyclerView.ViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BannerViewHolder(
            HomeBannerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BannerViewHolder -> {
                holder.setView(getItem(position))
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Banner>() {

        override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
            return oldItem.image == newItem.image
        }
    }
}

class BannerViewHolder(val binding: HomeBannerItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setView(data: Banner) {
        binding.bannerData = data
        binding.handlers = this
        binding.executePendingBindings()

        Logger.logD("BannerViewHolder", "setView, title=${binding.bannerData?.title}")
    }

    fun onClickBanner(view: View) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(binding.bannerData?.target_url))
            view.context.startActivity(intent)
        } catch (e: Exception) {
            Logger.logD("BannerViewHolder", "ACTION_VIEW intent error")
        }
    }
}

