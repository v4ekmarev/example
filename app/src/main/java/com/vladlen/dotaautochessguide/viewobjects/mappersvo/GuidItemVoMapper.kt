package com.vladlen.dotaautochessguide.viewobjects.mappersvo

import com.vladlen.dotaautochessguide.model.pojo.GuideItemPojo
import com.vladlen.dotaautochessguide.model.vo.guide.GuideItemVo

class GuidItemVoMapper {
    companion object {
        fun fromDto(guideItemListPojo: List<GuideItemPojo>) : MutableList<GuideItemVo> {
            val guideItemVoList = mutableListOf<GuideItemVo>()
            guideItemListPojo.forEach {
                guideItemVoList.add(GuideItemVo(it.title, it.image, it.desc, it.id))
            }
            return guideItemVoList
        }
    }
}
