package com.vladlen.dotaautochessguide.screens.tierlist.pagers.tier.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.tier.TierPagerViewModel
import com.vladlen.dotaautochessguide.usecases.tierlist.GetTierListUseCase

class TierPagerFactory(private val getTierListUseCase: GetTierListUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TierPagerViewModel(getTierListUseCase) as T
    }
}