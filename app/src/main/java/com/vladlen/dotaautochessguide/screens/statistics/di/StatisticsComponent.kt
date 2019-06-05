package com.vladlen.dotaautochessguide.screens.statistics.di

import com.vladlen.dotaautochessguide.application.di.AppComponent
import com.vladlen.dotaautochessguide.application.di.scope.FragmentScope
import com.vladlen.dotaautochessguide.screens.statistics.StatisticsFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [StatisticsModule::class])
interface StatisticsComponent {
    fun inject(statisticsFragment: StatisticsFragment)
}