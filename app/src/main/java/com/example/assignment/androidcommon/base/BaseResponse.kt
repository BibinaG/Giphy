package com.example.assignment.androidcommon.base

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("message")
    val message: String? = null
}

open class BaseErrorEntity(
    @SerializedName("error")
    open var error: String? = null,
    @SerializedName("fail")
    open var fail: String? = null,
    @SerializedName("detail")
    open var detail: String? = null,
    @SerializedName("non_field_errors")
    val nonFieldErrors: List<String>? = null,
    @SerializedName("errors")
    var errors: Errors? = null,
) : BaseResponse()


data class Errors(
    val email: List<String?>?,
    val password: List<String?>?,
)