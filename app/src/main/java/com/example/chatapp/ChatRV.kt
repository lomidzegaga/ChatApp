package com.example.chatapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.chatapp.databinding.MeItemBinding
import com.example.chatapp.databinding.UserItemBinding

class ChatRV(
    private val chat: MutableList<ChatModel>
) : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        const val USER_CHAT = 1
        const val ME_CHAT = 2
    }

    inner class UserChatViewHolder(
        private val binding: UserItemBinding
    ) : ViewHolder(binding.root) {
        fun bind() {
            val message = chat[adapterPosition]

            binding.textView.text = message.message
        }

    }

    inner class MeChatViewHolder(
        private val binding: MeItemBinding
    ) : ViewHolder(binding.root) {
        fun bind() {
            val message = chat[adapterPosition]

            binding.textView.text = message.message
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == USER_CHAT) {
            UserChatViewHolder(
                UserItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            MeChatViewHolder(
                MeItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItemViewType(position) == USER_CHAT) {
            (holder as UserChatViewHolder).bind()
        } else {
            (holder as MeChatViewHolder).bind()
        }
    }

    override fun getItemCount(): Int = chat.size

    override fun getItemViewType(position: Int): Int {
        return if (chat[position].isUser) {
            USER_CHAT
        } else {
            ME_CHAT
        }
    }

}