package com.vladlen.dotaautochessguide.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class MyError {
    @SerializedName("code")
    @Expose
    private val code: String? = null
    @SerializedName("message")
    @Expose
    private val message: String? = null
    @SerializedName("field")
    @Expose
    private val field: String? = null
}