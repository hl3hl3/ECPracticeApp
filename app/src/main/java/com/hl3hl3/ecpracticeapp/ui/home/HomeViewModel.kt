package com.hl3hl3.ecpracticeapp.ui.home

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hl3hl3.ecpracticeapp.Logger
import com.hl3hl3.ecpracticeapp.api.Api
import com.hl3hl3.ecpracticeapp.api.ApiResponse
import com.hl3hl3.ecpracticeapp.ui.message.MessageActivity
import com.hl3hl3.ecpracticeapp.vo.Banner
import com.hl3hl3.ecpracticeapp.vo.BannerResponse
import kotlinx.coroutines.launch

class HomeViewModel(): ViewModel() {

    var bannerData: ObservableField<List<Banner>> = ObservableField()

    fun onStart(context: Context) {
        viewModelScope.launch {
            val apiResponse: ApiResponse<BannerResponse> = Api.getBanners(context)
            if (apiResponse.response?.status_code == 0) {
                // success
                Logger.logD("HomeViewModel", "response, ${apiResponse.response}")
                Logger.logD("HomeViewModel", "result, ${apiResponse.response?.result}")

                bannerData.set(apiResponse.response?.result?.banners)
                Logger.logD("HomeViewModel", "success")
            } else {
                // fail
                bannerData.set(null)
                Logger.logD("HomeViewModel", "fail")
            }
        }
    }

    fun onClickPay(view: View) {
        // TODO
    }

    fun onClickMessage(view: View) {
        view.context.let {
            it.startActivity(Intent(it, MessageActivity::class.java))
        }
    }
}
