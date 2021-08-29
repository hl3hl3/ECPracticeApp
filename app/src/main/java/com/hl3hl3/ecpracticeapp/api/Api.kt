package com.hl3hl3.ecpracticeapp.api

import android.content.Context
import android.os.Build
import com.google.gson.Gson
import com.hl3hl3.ecpracticeapp.BuildConfig
import com.hl3hl3.ecpracticeapp.vo.BannerResponse
import com.hl3hl3.ecpracticeapp.vo.MessageResponse
import com.hl3hl3.ecpracticeapp.vo.QRCodeInfoResponse
import org.chromium.net.CronetEngine
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


object Api {

    suspend fun getBanners(context: Context): ApiResponse<BannerResponse> {
        return suspendCoroutine { continuation ->
            doGet(
                context,
                "/v3/f6733f2d-82fc-43e7-b19d-d8381f0ab91e",
                mapOf(headerAppVersion()),
                object : UrlRequestCallback<BannerResponse>() {
                    override fun onResponseSucceeded(apiResponse: ApiResponse<BannerResponse>) {
                        apiResponse.responseBodyString?.let {
                            apiResponse.response = Gson().fromJson(it, BannerResponse::class.java)
                        }
                        continuation.resume(apiResponse)
                    }

                    override fun onResponseFailed(apiResponse: ApiResponse<BannerResponse>) {
                        continuation.resume(apiResponse)
                    }

                }
            )
        }
    }

    suspend fun getMessages(context: Context): ApiResponse<MessageResponse> {
        return suspendCoroutine { continuation ->
            doGet(
                context,
                "/v3/0f0488e1-e532-45e5-8033-bef5904359fe",
                mapOf(headerAppVersion()),
                object : UrlRequestCallback<MessageResponse>() {
                    override fun onResponseSucceeded(apiResponse: ApiResponse<MessageResponse>) {
                        apiResponse.responseBodyString?.let {
                            apiResponse.response = Gson().fromJson(it, MessageResponse::class.java)
                        }
                        continuation.resume(apiResponse)
                    }

                    override fun onResponseFailed(apiResponse: ApiResponse<MessageResponse>) {
                        continuation.resume(apiResponse)
                    }

                }
            )
        }
    }

    suspend fun getQrcodeInfo(context: Context): ApiResponse<QRCodeInfoResponse> {
        return suspendCoroutine { continuation ->
            doGet(
                context,
                "/v3/8c29aeec-3ab4-4ac1-9b2e-e99652dbd155",
                mapOf(headerAppVersion()),
                object : UrlRequestCallback<QRCodeInfoResponse>() {
                    override fun onResponseSucceeded(apiResponse: ApiResponse<QRCodeInfoResponse>) {
                        apiResponse.responseBodyString?.let {
                            apiResponse.response = Gson().fromJson(it, QRCodeInfoResponse::class.java)
                        }
                        continuation.resume(apiResponse)
                    }

                    override fun onResponseFailed(apiResponse: ApiResponse<QRCodeInfoResponse>) {
                        continuation.resume(apiResponse)
                    }

                }
            )
        }
    }

    private fun headerAppVersion(): Pair<String, String> {
        return Pair("app_version", "Android${Build.VERSION.RELEASE}_v${BuildConfig.VERSION_NAME}")
    }

    const val baseUrl: String = "https://run.mocky.io"

    private fun <T> doGet(
        context: Context,
        url: String,
        headers: Map<String, String>?,
        callback: UrlRequestCallback<T>
    ) {
        val requestBuilder = CronetEngine.Builder(context).build().newUrlRequestBuilder(
            "$baseUrl$url",
            callback,
            Executors.newSingleThreadExecutor()
        ).setHttpMethod("GET")

        headers?.let {
            for (key in it.keys) {
                requestBuilder.addHeader(key, headers[key])
            }
        }

        requestBuilder.build().start()
    }


}




