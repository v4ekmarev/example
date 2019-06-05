package com.vladlen.dotaautochessguide.application.di

import android.content.Context
import com.vladlen.dotaautochessguide.application.rest.RestClient
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RetrofitModule::class
    ]
)
interface AppComponent {

    fun provideContext(): Context

    fun provideRestClient(): RestClient
}