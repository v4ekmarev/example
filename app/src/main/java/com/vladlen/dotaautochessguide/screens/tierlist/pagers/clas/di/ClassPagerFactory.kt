package com.vladlen.dotaautochessguide.screens.tierlist.pagers.clas.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.clas.ClassPagerViewModel
import com.vladlen.dotaautochessguide.usecases.tierlist.GetTierListUseCase

class ClassPagerFactory(private val getTierListUseCase: GetTierListUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ClassPagerViewModel(getTierListUseCase) as T
    }
}