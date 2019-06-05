package com.vladlen.dotaautochessguide.application.di

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vladlen.dotaautochessguide.R
import com.vladlen.dotaautochessguide.application.rest.ErrorInterceptor
import com.vladlen.dotaautochessguide.application.rest.RestClient
import com.vladlen.dotaautochessguide.application.rest.RxErrorHandlingCallAdapterFactory
import com.vladlen.dotaautochessguide.model.BaseUrl
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.*


@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): RestClient {
        return retrofit.create(RestClient::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(context: Context, gson: Gson, client: OkHttpClient): Retrofit {
        val baseUrl = BaseUrl.getBaseUrl(context)

        return Retrofit.Builder().baseUrl(baseUrl).addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create(
                context)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(
                GsonConverterFactory.create(gson)).client(client).build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(context: Context,
                            loggingInterceptor: HttpLoggingInterceptor,
                            sslSocketFactory: SSLSocketFactory,
                            trustManager: X509TrustManager,
                            interceptor: Interceptor): OkHttpClient {
        val connectTimeout = context.resources.getInteger(R.integer.connect_timeout_seconds).toLong()
        val readTimeout = context.resources.getInteger(R.integer.read_timeout_seconds).toLong()
        val writeTimeout = context.resources.getInteger(R.integer.write_timeout_seconds).toLong()
        val headerInterceptor = HttpLoggingInterceptor()
        headerInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(interceptor).addInterceptor(
                headerInterceptor).addNetworkInterceptor(ErrorInterceptor()).retryOnConnectionFailure(true).sslSocketFactory(
                sslSocketFactory,
                trustManager).connectTimeout(connectTimeout, TimeUnit.SECONDS).readTimeout(readTimeout,
                                                                                           TimeUnit.SECONDS).writeTimeout(
                writeTimeout,
                TimeUnit.SECONDS).build()
    }

    @Singleton
    @Provides
    fun provideTrushManager(): X509TrustManager {
        var trustManagerFactory: TrustManagerFactory? = null
        try {
            trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        try {
            trustManagerFactory?.init(null as KeyStore?)
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        }

        val trustManagers = trustManagerFactory?.trustManagers
        if (trustManagers?.size != 1 || trustManagers[0] !is X509TrustManager) {
            throw IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers))
        }
        return trustManagers[0] as X509TrustManager
    }

    @Singleton
    @Provides
    fun provideSSLSocketFactory(trustManager: X509TrustManager): SSLSocketFactory {
        var sslContext: SSLContext? = null
        try {
            sslContext = SSLContext.getInstance("SSL")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        try {
            sslContext!!.init(null, arrayOf<TrustManager>(trustManager), null)
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        }

        return sslContext!!.socketFactory
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(context: Context): Interceptor {

        return Interceptor {
            val originalRequest = it.request()
            val defaultLocale = Resources.getSystem().configuration.locale
            val versionApp = context.getString(R.string.apk_version)
            val clientLang = context.getString(R.string.client_lang)
            var version = ""
            try {
                version = context.packageManager.getPackageInfo(context.packageName, 0).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            val authorizedRequest =
                originalRequest.newBuilder()/*.header(clientLang, defaultLocale.toString())
                    .header(versionApp, version)*/.build()
            it.proceed(authorizedRequest)
        }
    }
}
