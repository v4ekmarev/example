package com.vladlen.dotaautochessguide.screens.tierlist.pagers.cost

import com.vladlen.dotaautochessguide.screens.tierlist.pagers.BasePagerViewModel
import com.vladlen.dotaautochessguide.usecases.tierlist.GetTierListUseCase

class CostPagerViewModel(getTierListUseCase: GetTierListUseCase) : BasePagerViewModel(getTierListUseCase), CostPagerContract {

    override fun onCleared() {
        super.onCleared()
    }
}