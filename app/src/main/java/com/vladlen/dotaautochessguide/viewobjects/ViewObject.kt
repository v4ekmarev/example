package com.vladlen.dotaautochessguide.viewobjects

import android.support.annotation.Nullable


class ViewObject<T>(status: Status?, data: T?, error: Throwable?) {
    var status: Status?
        internal set
    var data: T?
        internal set
    var error: Throwable?
        internal set

    init {
        this.status = status
        this.data = data
        this.error = error
    }

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }

    companion object {

        fun <T> loading(): ViewObject<T> {
            return ViewObject<T>(Status.LOADING, null, null)
        }

        fun <T> success(@Nullable data: T): ViewObject<T> {
            return ViewObject(Status.SUCCESS, data, null)
        }

        fun <T> error(@Nullable error: Throwable): ViewObject<T> {
            return ViewObject<T>(Status.ERROR, null, error)
        }
    }
}