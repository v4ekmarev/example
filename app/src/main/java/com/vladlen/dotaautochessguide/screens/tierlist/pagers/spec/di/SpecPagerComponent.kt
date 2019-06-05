package com.vladlen.dotaautochessguide.screens.tierlist.pagers.spec.di

import com.vladlen.dotaautochessguide.application.di.AppComponent
import com.vladlen.dotaautochessguide.application.di.scope.FragmentScope
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.spec.SpecPagerFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [SpecPagerModule::class])
interface SpecPagerComponent {
    fun inject(heroesPagerFragment: SpecPagerFragment)
}