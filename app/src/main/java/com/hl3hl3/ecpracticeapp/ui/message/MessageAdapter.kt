package com.hl3hl3.ecpracticeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hl3hl3.ecpracticeapp.databinding.MessageListItemBinding
import com.hl3hl3.ecpracticeapp.vo.Message

class MessageAdapter : ListAdapter<Message, RecyclerView.ViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MessageViewHolder(
            MessageListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MessageViewHolder -> {
                holder.setView(getItem(position))
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Message>() {

        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.ts == newItem.ts
        }
    }
}

class MessageViewHolder(val binding: MessageListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setView(data: Message) {
        binding.data = data
        binding.executePendingBindings()

        Logger.logD("MessageViewHolder", "setView, title=${binding.data?.title}")
    }
}

