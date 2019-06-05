package com.vladlen.dotaautochessguide.screens.tierlist.di

import dagger.Module
import dagger.Provides

@Module
class TierListModule {

    @Provides
    fun provideTierListFactory(): TierListFactory {
        return TierListFactory()
    }
}