package com.vladlen.dotaautochessguide.screens.tierlist.pagers.clas

import android.arch.lifecycle.LiveData
import com.vladlen.dotaautochessguide.model.vo.tier.TierVo
import com.vladlen.dotaautochessguide.viewobjects.ViewObject

interface ClassPagerContract {
    val getTierList: LiveData<ViewObject<MutableList<TierVo>>>
}