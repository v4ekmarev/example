package com.vladlen.dotaautochessguide.application.di

import android.content.Context
import com.vladlen.dotaautochessguide.application.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return app
    }
}