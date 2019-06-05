package com.vladlen.dotaautochessguide.model

import com.google.gson.annotations.SerializedName

class Response<T> {

    var data: T? = null
    var code: Status? = null
    var message: String? = null
    var myErrors: List<MyError>? = null

    enum class Status {
        @SerializedName("success")
        SUCCESS
    }
}