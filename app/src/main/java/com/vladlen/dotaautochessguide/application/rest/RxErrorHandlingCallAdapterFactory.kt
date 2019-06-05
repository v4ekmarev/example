package com.vladlen.dotaautochessguide.application.rest

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AlertDialog
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type


class RxErrorHandlingCallAdapterFactory : CallAdapter.Factory() {
    companion object {
        fun create(context: Context): CallAdapter.Factory {
            val value = RxErrorHandlingCallAdapterFactory()
            value.context = context
            return value
        }

        const val INVALID_TOKEN = 401
        const val INVALID_APK_VERSION = 426
    }

    var context: Context? = null

    private val _original by lazy {
        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *> {
        val wrapped = _original.get(returnType, annotations, retrofit) as CallAdapter<out Any, *>
        val wrapper = RxCallAdapterWrapper(retrofit, wrapped)
        wrapper.context = context
        return wrapper
    }

    private inner class RxCallAdapterWrapper<R>(val _retrofit: Retrofit,
                                                val _wrappedCallAdapter: CallAdapter<R, *>) : CallAdapter<R, Any> {
        var context: Context? = null
        override fun responseType(): Type = _wrappedCallAdapter.responseType()

        @Suppress("UNCHECKED_CAST")
        override fun adapt(call: Call<R>): Any {
            val adaptedCall = (_wrappedCallAdapter.adapt(call))

            if (adaptedCall is Completable) {
                return adaptedCall.onErrorResumeNext { throwable -> Completable.error(asRetrofitException(throwable)) }
            }

            if (adaptedCall is Single<*>) {
                return adaptedCall.onErrorResumeNext { throwable -> Single.error(asRetrofitException(throwable)) }
            }

            if (adaptedCall is Flowable<*>) {
                return adaptedCall.onErrorResumeNext(Function { t ->
                    Flowable.error(asRetrofitException(t))
                })
            }

            return adaptedCall
        }

        private fun asRetrofitException(throwable: Throwable): RetrofitException {
            if (throwable is HttpException) {
                val response = throwable.response()
                if (throwable.code() == INVALID_APK_VERSION) {
                    //TODO
                }
                return if (throwable.code() == 422) {
                    RetrofitException.httpErrorWithObject(response.raw().request().url().toString(), response, _retrofit)
                } else {
                    RetrofitException.httpError(response.raw().request().url().toString(), response, _retrofit)
                }
            }

            // A network error happened
            if (throwable is IOException) {
                return RetrofitException.networkError(throwable)
            }

            // We don't know what happened. We need to simply convert to an unknown error
            return RetrofitException.unexpectedError(throwable)
        }

    }
}