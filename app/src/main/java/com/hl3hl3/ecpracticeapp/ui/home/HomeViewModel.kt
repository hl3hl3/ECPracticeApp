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
import com.hl3hl3.ecpracticeapp.ui.LoadingView
import com.hl3hl3.ecpracticeapp.ui.message.MessageActivity
import com.hl3hl3.ecpracticeapp.ui.qrcodeinfo.QRCodeInfoActivity
import com.hl3hl3.ecpracticeapp.vo.Banner
import com.hl3hl3.ecpracticeapp.vo.BannerResponse
import kotlinx.coroutines.launch

class HomeViewModel(val loadingView: LoadingView): ViewModel() {

    var bannerData: ObservableField<List<Banner>> = ObservableField()

    fun onStart(context: Context) {
        loadingView.showLoading()
        viewModelScope.launch {
            val apiResponse: ApiResponse<BannerResponse> = Api.getBanners(context)
            if (apiResponse.response?.status_code == 0) {
                // success
                bannerData.set(apiResponse.response?.result?.banners)
            } else {
                // fail
                bannerData.set(null)
            }
            loadingView.hideLoading()
        }
    }

    fun onClickPay(view: View) {
        view.context.let {
            it.startActivity(Intent(it, QRCodeInfoActivity::class.java))
        }
    }

    fun onClickMessage(view: View) {
        view.context.let {
            it.startActivity(Intent(it, MessageActivity::class.java))
        }
    }
}
