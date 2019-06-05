package com.vladlen.dotaautochessguide.screens.tierlist.pagers.tier.di

import com.vladlen.dotaautochessguide.application.rest.RestClient
import com.vladlen.dotaautochessguide.usecases.tierlist.GetTierListUseCase
import dagger.Module
import dagger.Provides

@Module
class TierPagerModule {

    @Provides
    fun provideHeroesPagerFactory(getTierListUseCase: GetTierListUseCase): TierPagerFactory {
        return TierPagerFactory(getTierListUseCase)
    }

    @Provides
    fun provideGetTierListUseCase(restClient: RestClient): GetTierListUseCase {
        return GetTierListUseCase(restClient)
    }
}