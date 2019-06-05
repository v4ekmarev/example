package com.vladlen.dotaautochessguide.application.di.holder


abstract class BaseComponentHolder<T> : ComponentHolder<T> {

    override var component: T? = null

    override fun unbindComponent() {
        this.component = null
    }

    override fun bindComponent(component: T) {
        this.component = component
    }
}
