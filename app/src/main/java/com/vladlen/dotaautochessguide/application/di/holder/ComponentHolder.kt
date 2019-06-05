package com.vladlen.dotaautochessguide.application.di.holder

interface ComponentHolder<T> {

    val component: T?

    fun bindComponent(component: T)

    fun unbindComponent()
}