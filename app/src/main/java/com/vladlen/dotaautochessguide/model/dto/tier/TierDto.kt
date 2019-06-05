package com.vladlen.dotaautochessguide.model.dto.tier

import com.vladlen.dotaautochessguide.model.Constants

class TierDto {
    val name: String = Constants.EMPTY_STRING
    val items = mutableListOf<TierHeroDto>()
}