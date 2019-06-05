package com.vladlen.dotaautochessguide.screens.items.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vladlen.dotaautochessguide.screens.items.ItemsViewModel

class ItemsFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ItemsViewModel() as T
    }
}