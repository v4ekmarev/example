package com.vladlen.dotaautochessguide.model

import android.content.Context
import com.vladlen.dotaautochessguide.R

class BaseUrl {
    companion object {
        fun getBaseUrl(context: Context): String {
            return context.resources.getString(R.string.api_url)
        }
    }
}