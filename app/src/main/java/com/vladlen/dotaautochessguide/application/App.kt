package com.vladlen.dotaautochessguide.application

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.vladlen.dotaautochessguide.BuildConfig
import com.vladlen.dotaautochessguide.application.di.AppComponentHolder
import com.vladlen.dotaautochessguide.application.di.AppModule
import com.vladlen.dotaautochessguide.application.di.DaggerAppComponent
import io.fabric.sdk.android.Fabric




class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initAppComponent()

        initCrashlytics()
    }

    private fun initCrashlytics() {
        val crashlyticsKit = Crashlytics.Builder().core(CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()).build()
        Fabric.with(this, crashlyticsKit)
    }

    private fun initAppComponent() {
        // There is no need to unbind this component, because the system kills the process,
        // accordingly, all objects created by this process are destroyed.
        AppComponentHolder.instance.bindComponent(
            DaggerAppComponent.builder()
                .appModule(AppModule(this))// TODO: Replace with @Component.Builder
                .build()
        )
    }
}