package com.hl3hl3.ecpracticeapp.ui.message

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hl3hl3.ecpracticeapp.Logger
import com.hl3hl3.ecpracticeapp.api.Api
import com.hl3hl3.ecpracticeapp.api.ApiResponse
import com.hl3hl3.ecpracticeapp.ui.LoadingView
import com.hl3hl3.ecpracticeapp.vo.Message
import com.hl3hl3.ecpracticeapp.vo.MessageResponse
import kotlinx.coroutines.launch

class MessageViewModel(val loadingView: LoadingView) : ViewModel() {

    var data: ObservableField<List<Message>> = ObservableField()
    var isEditMode: ObservableField<Boolean> = ObservableField(false)

    fun onStart(context: Context) {
        loadingView.showLoading()
        viewModelScope.launch {
            val apiResponse: ApiResponse<MessageResponse> = Api.getMessages(context)
            if (apiResponse.response?.status_code == 0) {
                // success
                data.set(apiResponse.response?.result?.messages)
            } else {
                // fail
                data.set(null)
            }
            loadingView.hideLoading()
        }
    }

    fun onClickEdit(view: View) {
        isEditMode.set(isEditMode.get()?.not() ?: true)
    }

    fun onClickRemoveMessage(messageToRemove: Message) {
        data.get()?.toMutableList()?.let {

            var indexToRemove: Int = -1
            it.forEachIndexed { index, message ->
                if (message.isSame(messageToRemove)) {
                    indexToRemove = index
                }
            }

            if (it.size <= indexToRemove) {
                Logger.logD(
                    "MessageViewModel",
                    "index wrong, list size=${it.size}, remove position=$indexToRemove"
                )
            } else {
                it.removeAt(indexToRemove)
                data.set(it)
            }
        }
    }

}