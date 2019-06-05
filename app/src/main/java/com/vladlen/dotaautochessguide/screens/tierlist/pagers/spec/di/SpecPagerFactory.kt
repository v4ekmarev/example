package com.vladlen.dotaautochessguide.screens.tierlist.pagers.spec.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.spec.SpecPagerViewModel
import com.vladlen.dotaautochessguide.usecases.tierlist.GetTierListUseCase

class SpecPagerFactory(private val getTierListUseCase: GetTierListUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SpecPagerViewModel(getTierListUseCase) as T
    }
}