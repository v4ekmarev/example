package com.vladlen.dotaautochessguide.usecases.guides

import com.vladlen.dotaautochessguide.application.rest.RestClient
import com.vladlen.dotaautochessguide.model.pojo.GuideItemPojo
import com.vladlen.dotaautochessguide.usecases.BaseUseCase
import com.vladlen.dotaautochessguide.viewobjects.mappersdto.GuidItemDtoMapper
import io.reactivex.Flowable

class GetGuidesItemUseCase(private val restClient: RestClient) : BaseUseCase<List<GuideItemPojo>>() {
    override fun buildUseCaseObservable(): Flowable<List<GuideItemPojo>> {
        return restClient.getGuidesList().map { response ->
            val guideItemPojoList = mutableListOf<GuideItemPojo>()
            response.data?.forEach {
                guideItemPojoList.add(GuidItemDtoMapper.fromDto(it))
            }
            return@map guideItemPojoList
        }
    }
}