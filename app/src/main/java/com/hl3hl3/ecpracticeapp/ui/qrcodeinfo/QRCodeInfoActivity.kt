package com.hl3hl3.ecpracticeapp.ui.qrcodeinfo

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hl3hl3.ecpracticeapp.MessageAdapter
import com.hl3hl3.ecpracticeapp.R
import com.hl3hl3.ecpracticeapp.databinding.MessageActivityBinding
import com.hl3hl3.ecpracticeapp.databinding.QrcodeInfoActivityBinding
import com.hl3hl3.ecpracticeapp.ui.BaseActivity

class QRCodeInfoActivity  : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: QrcodeInfoActivityBinding = DataBindingUtil.setContentView(this,
            R.layout.qrcode_info_activity
        )
        val viewModel = QRCodeInfoViewModel()

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        viewModel.onStart(this)

    }
}