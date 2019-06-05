package com.vladlen.dotaautochessguide.screens.guides.guidcontent.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vladlen.dotaautochessguide.screens.guides.guidcontent.GuideContentViewModel
import com.vladlen.dotaautochessguide.usecases.guides.GetGuideContentUseCase

class GuideContentFactory(private val getGuideContentUseCase: GetGuideContentUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GuideContentViewModel(getGuideContentUseCase) as T
    }
}