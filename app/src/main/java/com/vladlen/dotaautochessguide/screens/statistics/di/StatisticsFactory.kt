package com.vladlen.dotaautochessguide.screens.statistics.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class StatisticsFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StatisticsFactory() as T
    }
}