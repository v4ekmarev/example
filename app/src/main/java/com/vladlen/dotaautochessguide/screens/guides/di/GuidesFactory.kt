package com.vladlen.dotaautochessguide.screens.guides.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vladlen.dotaautochessguide.screens.guides.GuidesViewModel
import com.vladlen.dotaautochessguide.usecases.guides.GetGuidesItemUseCase

class GuidesFactory(private val getGuideListUseCase: GetGuidesItemUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GuidesViewModel(getGuideListUseCase) as T
    }
}