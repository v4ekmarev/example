package com.vladlen.dotaautochessguide.screens.items.di

import dagger.Module
import dagger.Provides

@Module
class ItemsModule {

    @Provides
    fun provideItemsFactory( ): ItemsFactory {
        return ItemsFactory()
    }
}