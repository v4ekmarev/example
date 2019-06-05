package com.vladlen.dotaautochessguide.usecases.guides

import com.vladlen.dotaautochessguide.application.rest.RestClient
import com.vladlen.dotaautochessguide.model.vo.guide.GuideContentVo
import com.vladlen.dotaautochessguide.usecases.BaseUseCase
import com.vladlen.dotaautochessguide.viewobjects.mappersvo.GuidContentVoMapper
import io.reactivex.Flowable

class GetGuideContentUseCase(private val restClient: RestClient) : BaseUseCase<GuideContentVo>() {
    private lateinit var id : String
    override fun buildUseCaseObservable(): Flowable<GuideContentVo> {
        return restClient.getGuideContent(id).map { response ->
            return@map response.data?.let { GuidContentVoMapper.fromDto(it) }
        }
    }

    fun execute(id: String): Flowable<GuideContentVo> {
        this.id = id
        return execute()
    }
}