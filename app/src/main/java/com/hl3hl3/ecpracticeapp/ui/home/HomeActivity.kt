package com.hl3hl3.ecpracticeapp.ui.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.hl3hl3.ecpracticeapp.BannerAdapter
import com.hl3hl3.ecpracticeapp.R
import com.hl3hl3.ecpracticeapp.databinding.HomeActivityBinding
import com.hl3hl3.ecpracticeapp.ui.BaseActivity


class HomeActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: HomeActivityBinding = DataBindingUtil.setContentView(this,
            R.layout.home_activity
        )
        val viewModel = HomeViewModel(this)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        binding.rvHomeBanner.let {
            it.layoutManager = LinearLayoutManager(this).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            it.adapter = BannerAdapter()
            PagerSnapHelper().attachToRecyclerView(it)
        }

        binding.toolbar.let {
            setSupportActionBar(it)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(false)
                setDisplayShowHomeEnabled(false)
            }
        }

        viewModel.onStart(this)

    }

}