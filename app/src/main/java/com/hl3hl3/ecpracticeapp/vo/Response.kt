package com.hl3hl3.ecpracticeapp.vo

open class Response<T>(
    val status_code: Int = 0,
    val result: T? = null,
)