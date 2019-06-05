package com.vladlen.dotaautochessguide.screens.guides.di

import com.vladlen.dotaautochessguide.application.rest.RestClient
import com.vladlen.dotaautochessguide.usecases.guides.GetGuidesItemUseCase
import dagger.Module
import dagger.Provides

@Module
class GuidesModule {

    @Provides
    fun provideGuidesFactory(getGuideListUseCase: GetGuidesItemUseCase): GuidesFactory {
        return GuidesFactory(getGuideListUseCase)
    }

    @Provides
    fun provideGetGuidListUseCase(restClient: RestClient): GetGuidesItemUseCase {
        return GetGuidesItemUseCase(restClient)
    }
}