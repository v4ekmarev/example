package com.vladlen.dotaautochessguide.viewobjects.mappersdto

import com.vladlen.dotaautochessguide.model.dto.GuideItemDto
import com.vladlen.dotaautochessguide.model.pojo.GuideItemPojo

class GuidItemDtoMapper {
    companion object {
        fun fromDto(guideItemDto: GuideItemDto) : GuideItemPojo {
            return GuideItemPojo(guideItemDto.title, guideItemDto.image, guideItemDto.desc, guideItemDto.id)
        }
    }
}
