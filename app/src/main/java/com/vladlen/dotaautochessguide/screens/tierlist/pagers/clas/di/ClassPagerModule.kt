package com.vladlen.dotaautochessguide.screens.tierlist.pagers.clas.di
import com.vladlen.dotaautochessguide.application.rest.RestClient
import com.vladlen.dotaautochessguide.usecases.tierlist.GetTierListUseCase
import dagger.Module
import dagger.Provides

@Module
class ClassPagerModule {

    @Provides
    fun provideHeroesPagerFactory(getTierListUseCase: GetTierListUseCase): ClassPagerFactory {
        return ClassPagerFactory(getTierListUseCase)
    }

    @Provides
    fun provideGetTierListUseCase(restClient: RestClient): GetTierListUseCase {
        return GetTierListUseCase(restClient)
    }
}