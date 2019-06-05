package com.vladlen.dotaautochessguide.application.rest

import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {

        val originalResponse = chain!!.proceed(chain.request())

        if (shouldLogout()) {
            // your logout logic here

            // send empty response down the chain
            return Response.Builder().build()

        }
        return originalResponse
    }

    @SuppressWarnings("unused")
    private fun shouldLogout(): Boolean {
        return false
    }
}