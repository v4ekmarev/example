package com.vladlen.dotaautochessguide.usecases

import io.reactivex.Flowable


abstract class BaseUseCase<T> protected constructor() {

    fun execute(): Flowable<T> {
        return buildUseCaseObservable()
            .retryWhen { flowable ->
                flowable.flatMap { throwable ->
                    return@flatMap Flowable.error<Throwable>(throwable)
                }
            }
    }

    protected abstract fun buildUseCaseObservable(): Flowable<T>
}
