package com.vladlen.dotaautochessguide.screens.tierlist.pagers.spec

import com.vladlen.dotaautochessguide.screens.tierlist.pagers.BasePagerViewModel
import com.vladlen.dotaautochessguide.usecases.tierlist.GetTierListUseCase

class SpecPagerViewModel(getTierListUseCase: GetTierListUseCase) : BasePagerViewModel(getTierListUseCase), SpecPagerContract {

    override fun onCleared() {
        super.onCleared()
    }
}