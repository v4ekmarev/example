package com.vladlen.dotaautochessguide.screens.guides.di

import com.vladlen.dotaautochessguide.application.di.AppComponent
import com.vladlen.dotaautochessguide.application.di.scope.FragmentScope
import com.vladlen.dotaautochessguide.screens.guides.GuidesFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [GuidesModule::class])
interface GuidesComponent {
    fun inject(guidesFragment: GuidesFragment)
}