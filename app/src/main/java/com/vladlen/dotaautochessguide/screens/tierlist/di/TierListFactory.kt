package com.vladlen.dotaautochessguide.screens.tierlist.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vladlen.dotaautochessguide.screens.tierlist.TierListViewModel

class TierListFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TierListViewModel() as T
    }
}