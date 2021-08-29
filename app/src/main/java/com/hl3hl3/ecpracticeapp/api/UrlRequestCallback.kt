package com.hl3hl3.ecpracticeapp.api

import com.hl3hl3.ecpracticeapp.Logger
import org.chromium.net.CronetException
import org.chromium.net.UrlRequest
import org.chromium.net.UrlResponseInfo
import java.nio.ByteBuffer
import java.nio.charset.Charset

abstract class UrlRequestCallback<T>(
    val shouldFollowRedirect: Boolean = true,
) : UrlRequest.Callback() {
    private val TAG = "MyUrlRequestCallback"

    var httpStatusCode: Int? = null
    var responseHeaders: Map<String, List<String>>? = null
    var responseBodyString: String? = null

    abstract fun onResponseSucceeded(apiResponse: ApiResponse<T>)
    abstract fun onResponseFailed(apiResponse: ApiResponse<T>)

    override fun onRedirectReceived(
        request: UrlRequest?,
        info: UrlResponseInfo?,
        newLocationUrl: String?
    ) {
        if (shouldFollowRedirect) {
            request?.followRedirect()
        } else {
            request?.cancel()
        }
    }

    override fun onResponseStarted(request: UrlRequest?, info: UrlResponseInfo?) {
        request?.read(ByteBuffer.allocateDirect(1024 * 32))
    }

    override fun onReadCompleted(
        request: UrlRequest?,
        info: UrlResponseInfo?,
        byteBuffer: ByteBuffer?
    ) {
        httpStatusCode = info?.httpStatusCode
        responseHeaders = info?.allHeaders

        byteBuffer?.flip()
        byteBuffer?.let {
            val bytes = ByteArray(it.remaining())
            it.get(bytes)
            responseBodyString = String(bytes, Charset.forName("UTF-8"))
        }

        // You should keep reading the request until there's no more data.
        byteBuffer?.clear()
        request?.read(byteBuffer)
    }

    override fun onSucceeded(request: UrlRequest?, info: UrlResponseInfo?) {
        Logger.logD(TAG, "onSucceeded method called.")
        logRequest()
        onResponseSucceeded(ApiResponse(
            httpStatusCode,
            responseHeaders,
            responseBodyString
        ))
    }

    override fun onFailed(request: UrlRequest?, info: UrlResponseInfo?, error: CronetException?) {
        Logger.logD(TAG, "onFailed method called.")
        logRequest()
        onResponseFailed(ApiResponse(
            httpStatusCode,
            responseHeaders,
            responseBodyString
        ))
    }

    private fun logRequest() {
        httpStatusCode?.let {
            Logger.logD(TAG, "status code = $httpStatusCode")
        }
        responseHeaders?.let { it ->
            val sb = StringBuilder()
            for (key in it.keys) {
                sb.append(key).append("=[").append(it.get(key)).append("]\n")
            }
            Logger.logD(TAG, sb.toString())
        }
        responseBodyString?.let {
            Logger.logD(TAG, "body= $responseBodyString")
        }
    }
}