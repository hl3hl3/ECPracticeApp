package com.hl3hl3.ecpracticeapp.api

import android.content.Context
import com.google.gson.Gson
import com.hl3hl3.ecpracticeapp.vo.BannerResponse
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
                mapOf(
                    Pair("app_version", "Android11_v1.0")
                ),
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




