package com.vladlen.dotaautochessguide.model.vo.tier

import com.vladlen.dotaautochessguide.model.Constants

class TierVo(val className: String = Constants.EMPTY_STRING, val tierHeroList: MutableList<TierHeroVo> = mutableListOf<TierHeroVo>()) {
}