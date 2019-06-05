package com.vladlen.dotaautochessguide.screens.guides.guidcontent

import android.arch.lifecycle.LiveData
import com.vladlen.dotaautochessguide.model.vo.guide.GuideContentVo
import com.vladlen.dotaautochessguide.viewobjects.ViewObject

interface GuideContentContract {
    val getGuideContent: LiveData<ViewObject<GuideContentVo>>
}