package com.vladlen.dotaautochessguide.viewobjects.mappersvo.tier

import com.vladlen.dotaautochessguide.model.dto.tier.TierDto
import com.vladlen.dotaautochessguide.model.vo.tier.TierVo

class TierVoMapper {
    companion object {
        fun fromDto(tierListDto: List<TierDto>): MutableList<TierVo> {
            val tierVoList = mutableListOf<TierVo>()
            tierListDto.forEach {
                tierVoList.add(TierVo(it.name, TierHeroVoMapper.fromDto(it.items)))
            }
            return tierVoList
        }
    }
}
