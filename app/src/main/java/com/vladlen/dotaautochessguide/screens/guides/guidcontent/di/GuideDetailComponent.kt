package com.vladlen.dotaautochessguide.screens.guides.guidcontent.di

import com.vladlen.dotaautochessguide.application.di.AppComponent
import com.vladlen.dotaautochessguide.application.di.scope.FragmentScope
import com.vladlen.dotaautochessguide.screens.guides.guidcontent.GuideContentFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [GuideDetailModule::class])
interface GuideDetailComponent {
    fun inject(guideContentFragment: GuideContentFragment)
}