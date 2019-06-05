package com.vladlen.dotaautochessguide.screens.tierlist.pagers.cost.di

import com.vladlen.dotaautochessguide.application.di.AppComponent
import com.vladlen.dotaautochessguide.application.di.scope.FragmentScope
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.cost.CostPagerFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [CostPagerModule::class])
interface CostPagerComponent {
    fun inject(costPagerFragment: CostPagerFragment)
}