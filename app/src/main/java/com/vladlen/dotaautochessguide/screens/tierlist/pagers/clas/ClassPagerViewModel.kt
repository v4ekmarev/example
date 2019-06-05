package com.vladlen.dotaautochessguide.screens.tierlist.pagers.clas

import com.vladlen.dotaautochessguide.screens.tierlist.pagers.BasePagerViewModel
import com.vladlen.dotaautochessguide.usecases.tierlist.GetTierListUseCase

class ClassPagerViewModel(getTierListUseCase: GetTierListUseCase) : BasePagerViewModel(getTierListUseCase), ClassPagerContract {

    override fun onCleared() {
        super.onCleared()
    }
}