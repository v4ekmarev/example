package com.vladlen.dotaautochessguide.screens.tierlist.pagers.clas.di

import com.vladlen.dotaautochessguide.application.di.AppComponent
import com.vladlen.dotaautochessguide.application.di.scope.FragmentScope
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.clas.ClassPagerFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [ClassPagerModule::class])
interface ClassPagerComponent {
    fun inject(classPagerFragment: ClassPagerFragment)
}