package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val chat = mutableListOf<ChatModel>()

    private val adapter = ChatRV(chat)
    private var isUser = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this).apply {
            stackFromEnd = true
        }

        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            isUser = binding.userRB.isChecked
        }

        binding.sendIV.setOnClickListener {
            chat.add(ChatModel(message = binding.textInput.text.toString(), isUser))
            binding.recyclerView.smoothScrollToPosition(chat.size - 1)
            binding.textInput.setText("")
            adapter.notifyDataSetChanged()
        }
    }
}