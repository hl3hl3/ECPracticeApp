package com.hl3hl3.ecpracticeapp.ui.qrcodeinfo

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hl3hl3.ecpracticeapp.Logger
import com.hl3hl3.ecpracticeapp.api.Api
import com.hl3hl3.ecpracticeapp.api.ApiResponse
import com.hl3hl3.ecpracticeapp.vo.Message
import com.hl3hl3.ecpracticeapp.vo.MessageResponse
import com.hl3hl3.ecpracticeapp.vo.QRCodeInfo
import com.hl3hl3.ecpracticeapp.vo.QRCodeInfoResponse
import kotlinx.coroutines.launch

class QRCodeInfoViewModel : ViewModel() {

    var data: ObservableField<QRCodeInfo> = ObservableField()

    fun onStart(context: Context) {
        viewModelScope.launch {
            Logger.logD(this.javaClass.name, "viewModelScope.launch")
            val apiResponse: ApiResponse<QRCodeInfoResponse> = Api.getQrcodeInfo(context)
            if (apiResponse.response?.status_code == 0) {
                // success
                Logger.logD(this.javaClass.name, "response, ${apiResponse.response}")
                Logger.logD(this.javaClass.name, "result, ${apiResponse.response?.result}")

                data.set(apiResponse.response?.result)
                Logger.logD(this.javaClass.name, "success")
            } else {
                // fail
                data.set(null)
                Logger.logD(this.javaClass.name, "fail")
            }
        }
    }

}