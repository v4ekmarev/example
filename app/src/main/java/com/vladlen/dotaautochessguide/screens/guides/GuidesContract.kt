package com.vladlen.dotaautochessguide.screens.guides

import android.arch.lifecycle.LiveData
import com.vladlen.dotaautochessguide.model.vo.guide.GuideItemVo
import com.vladlen.dotaautochessguide.viewobjects.ViewObject

interface GuidesContract {
    val getGuideList: LiveData<ViewObject<MutableList<GuideItemVo>>>
}