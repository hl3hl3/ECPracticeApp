package com.hl3hl3.ecpracticeapp.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), LoadingView {

    private var progressDialog: ProgressDialog? = null
    override fun showLoading() {
        progressDialog = progressDialog ?: ProgressDialog(this).apply {
            setMessage("載入中...")
            setCancelable(false)
        }
        progressDialog?.show()
    }

    override fun hideLoading() {
        progressDialog?.dismiss()
        progressDialog = null
    }
}

interface LoadingView {
    fun showLoading()
    fun hideLoading()
}