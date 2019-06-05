package com.vladlen.dotaautochessguide.screens.tierlist.pagers.tier

import com.vladlen.dotaautochessguide.screens.tierlist.pagers.BasePagerViewModel
import com.vladlen.dotaautochessguide.usecases.tierlist.GetTierListUseCase

class TierPagerViewModel(getTierListUseCase: GetTierListUseCase) : BasePagerViewModel(getTierListUseCase), TierPagerContract {

    override fun onCleared() {
        super.onCleared()
    }
}