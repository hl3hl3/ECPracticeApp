package com.hl3hl3.ecpracticeapp.ui.qrcodeinfo

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hl3hl3.ecpracticeapp.api.Api
import com.hl3hl3.ecpracticeapp.api.ApiResponse
import com.hl3hl3.ecpracticeapp.ui.LoadingView
import com.hl3hl3.ecpracticeapp.vo.QRCodeInfo
import com.hl3hl3.ecpracticeapp.vo.QRCodeInfoResponse
import kotlinx.coroutines.launch

class QRCodeInfoViewModel(val loadingView: LoadingView) : ViewModel() {

    var data: ObservableField<QRCodeInfo> = ObservableField()

    fun onStart(context: Context) {
        loadingView.showLoading()
        viewModelScope.launch {
            val apiResponse: ApiResponse<QRCodeInfoResponse> = Api.getQrcodeInfo(context)
            if (apiResponse.response?.status_code == 0) {
                // success
                data.set(apiResponse.response?.result)
            } else {
                // fail
                data.set(null)
            }
            loadingView.hideLoading()
        }
    }

}