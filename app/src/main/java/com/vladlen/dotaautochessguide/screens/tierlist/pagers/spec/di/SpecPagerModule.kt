package com.vladlen.dotaautochessguide.screens.tierlist.pagers.spec.di

import com.vladlen.dotaautochessguide.application.rest.RestClient
import com.vladlen.dotaautochessguide.usecases.tierlist.GetTierListUseCase
import dagger.Module
import dagger.Provides

@Module
class SpecPagerModule {

    @Provides
    fun provideHeroesPagerFactory(getTierListUseCase: GetTierListUseCase): SpecPagerFactory {
        return SpecPagerFactory(getTierListUseCase)
    }

    @Provides
    fun provideGetTierListUseCase(restClient: RestClient): GetTierListUseCase {
        return GetTierListUseCase(restClient)
    }
}