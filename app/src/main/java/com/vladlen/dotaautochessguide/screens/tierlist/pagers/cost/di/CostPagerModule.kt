package com.vladlen.dotaautochessguide.screens.tierlist.pagers.cost.di

import com.vladlen.dotaautochessguide.application.rest.RestClient
import com.vladlen.dotaautochessguide.usecases.tierlist.GetTierListUseCase
import dagger.Module
import dagger.Provides

@Module
class CostPagerModule {

    @Provides
    fun provideHeroesPagerFactory(getTierListUseCase: GetTierListUseCase): CostPagerFactory {
        return CostPagerFactory(getTierListUseCase)
    }

    @Provides
    fun provideGetTierListUseCase(restClient: RestClient): GetTierListUseCase {
        return GetTierListUseCase(restClient)
    }
}