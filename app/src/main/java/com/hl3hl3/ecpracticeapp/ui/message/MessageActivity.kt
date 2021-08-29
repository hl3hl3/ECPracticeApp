package com.hl3hl3.ecpracticeapp.ui.message

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hl3hl3.ecpracticeapp.MessageAdapter
import com.hl3hl3.ecpracticeapp.R
import com.hl3hl3.ecpracticeapp.databinding.MessageActivityBinding
import com.hl3hl3.ecpracticeapp.ui.BaseActivity

class MessageActivity  : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: MessageActivityBinding = DataBindingUtil.setContentView(this,
            R.layout.message_activity
        )
        val viewModel = MessageViewModel()

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        binding.rvMessages.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = MessageAdapter()
        }

        viewModel.onStart(this)

    }
}