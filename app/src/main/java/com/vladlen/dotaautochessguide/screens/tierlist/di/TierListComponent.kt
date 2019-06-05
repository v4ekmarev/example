package com.vladlen.dotaautochessguide.screens.tierlist.di

import com.vladlen.dotaautochessguide.application.di.AppComponent
import com.vladlen.dotaautochessguide.application.di.scope.FragmentScope
import com.vladlen.dotaautochessguide.screens.tierlist.TierListFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [TierListModule::class])
interface TierListComponent {
    fun inject(tierListFragment: TierListFragment)
}