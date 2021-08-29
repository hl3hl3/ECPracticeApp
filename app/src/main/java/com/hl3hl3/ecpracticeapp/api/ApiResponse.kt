package com.hl3hl3.ecpracticeapp.api

data class ApiResponse<T>(
    val httpStatusCode: Int? = null,
    val responseHeaders: Map<String, List<String>>? = null,
    val responseBodyString: String? = null,
    var response: T? = null,
)