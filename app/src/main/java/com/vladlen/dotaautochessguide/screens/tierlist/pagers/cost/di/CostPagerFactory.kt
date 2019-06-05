package com.vladlen.dotaautochessguide.screens.tierlist.pagers.cost.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.cost.CostPagerViewModel
import com.vladlen.dotaautochessguide.usecases.tierlist.GetTierListUseCase

class CostPagerFactory(private val getTierListUseCase: GetTierListUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  CostPagerViewModel(getTierListUseCase) as T
    }
}