package com.vladlen.dotaautochessguide.viewobjects.mappersvo

import com.vladlen.dotaautochessguide.model.dto.GuideContentDto
import com.vladlen.dotaautochessguide.model.vo.guide.GuideContentVo

class GuidContentVoMapper {
    companion object {
        fun fromDto(guideContentDto: GuideContentDto) : GuideContentVo {
            val guidContentVo = GuideContentVo()
            guidContentVo.content = guideContentDto.content
            guidContentVo.desc = guideContentDto.desc
            guidContentVo.id = guideContentDto.id
            guidContentVo.image = guideContentDto.image
            guidContentVo.locale = guideContentDto.locale
            guidContentVo.title = guideContentDto.title
            return guidContentVo
        }
    }
}
