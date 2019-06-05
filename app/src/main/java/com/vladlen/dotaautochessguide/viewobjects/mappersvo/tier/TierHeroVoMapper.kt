package com.vladlen.dotaautochessguide.viewobjects.mappersvo.tier

import com.vladlen.dotaautochessguide.model.dto.tier.TierHeroDto
import com.vladlen.dotaautochessguide.model.vo.tier.TierHeroVo

class TierHeroVoMapper {
    companion object {
        fun fromDto(tierHeroListDto: List<TierHeroDto>) : MutableList<TierHeroVo> {
            val tierItemVoList = mutableListOf<TierHeroVo>()
            tierHeroListDto.forEach {
                tierItemVoList.add(TierHeroVo(it.index, it.hero, it.image))
            }
            return tierItemVoList
        }
    }
}
