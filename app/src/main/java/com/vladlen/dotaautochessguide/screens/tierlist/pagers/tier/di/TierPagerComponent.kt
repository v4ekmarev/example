package com.vladlen.dotaautochessguide.screens.tierlist.pagers.tier.di

import com.vladlen.dotaautochessguide.application.di.AppComponent
import com.vladlen.dotaautochessguide.application.di.scope.FragmentScope
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.tier.TierPagerFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [TierPagerModule::class])
interface TierPagerComponent {
    fun inject(tierPagerFragment: TierPagerFragment)
}