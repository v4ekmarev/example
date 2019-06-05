package com.vladlen.dotaautochessguide.screens.items.di

import com.vladlen.dotaautochessguide.application.di.AppComponent
import com.vladlen.dotaautochessguide.application.di.scope.FragmentScope
import com.vladlen.dotaautochessguide.screens.items.ItemsFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [ItemsModule::class])
interface ItemsComponent {
    fun inject(itemsFragment: ItemsFragment)
}