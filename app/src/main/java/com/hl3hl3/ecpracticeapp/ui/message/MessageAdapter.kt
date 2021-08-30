package com.hl3hl3.ecpracticeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hl3hl3.ecpracticeapp.databinding.MessageListItemBinding
import com.hl3hl3.ecpracticeapp.ui.message.MessageViewModel
import com.hl3hl3.ecpracticeapp.vo.Message

class MessageAdapter(val viewModel: MessageViewModel) : ListAdapter<Message, RecyclerView.ViewHolder>(DiffCallback), Listener{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MessageViewHolder(
            MessageListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), this
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MessageViewHolder -> {
                holder.setView(getItem(position), isEditMode, position)
            }
        }
    }

    private var isEditMode: Boolean = false

    fun setEditMode(value: Boolean) {
        isEditMode = value
        notifyDataSetChanged()
    }

    override fun onClickRemove(position: Int) {
        viewModel.onClickRemoveMessage(position)
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

interface Listener {
    fun onClickRemove(position: Int)
}

class MessageViewHolder(
    val binding: MessageListItemBinding,
    private val listener: Listener,
) : RecyclerView.ViewHolder(binding.root) {
    fun setView(data: Message, isEditMode: Boolean, position: Int) {
        binding.data = data
        binding.isEditMode = isEditMode
        binding.tvMessageRemove.tag = position
        binding.tvMessageRemove.setOnClickListener {
            listener.onClickRemove(it.tag as Int)
        }
        binding.executePendingBindings()
        Logger.logD("MessageViewHolder", "setView, title=${binding.data?.title}")
    }
}

