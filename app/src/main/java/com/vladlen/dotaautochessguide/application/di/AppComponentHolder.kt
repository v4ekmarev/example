package com.vladlen.dotaautochessguide.application.di

import com.vladlen.dotaautochessguide.application.di.holder.BaseComponentHolder


class AppComponentHolder private constructor() : BaseComponentHolder<AppComponent>() {
    companion object {
        val instance = AppComponentHolder()
    }
}