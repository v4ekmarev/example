package com.vladlen.dotaautochessguide.screens.guides.guidcontent.di

import com.vladlen.dotaautochessguide.application.rest.RestClient
import com.vladlen.dotaautochessguide.usecases.guides.GetGuideContentUseCase
import dagger.Module
import dagger.Provides

@Module
class GuideDetailModule {

    @Provides
    fun provideGuideDetailFactory(getGuideContentUseCase: GetGuideContentUseCase): GuideContentFactory {
        return GuideContentFactory(getGuideContentUseCase)
    }

    @Provides
    fun provideGetGuidContentUseCase(restClient: RestClient): GetGuideContentUseCase {
        return GetGuideContentUseCase(restClient)
    }
}