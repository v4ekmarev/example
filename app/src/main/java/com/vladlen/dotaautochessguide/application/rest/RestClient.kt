package com.vladlen.dotaautochessguide.application.rest

import com.vladlen.dotaautochessguide.model.Response
import com.vladlen.dotaautochessguide.model.dto.GuideContentDto
import com.vladlen.dotaautochessguide.model.dto.GuideItemDto
import com.vladlen.dotaautochessguide.model.dto.tier.TierDto
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


interface RestClient {

    @GET("/guides/v1/list")
    fun getGuidesList(): Flowable<Response<List<GuideItemDto>>>

    @GET("/guides/v1/get")
    fun getGuideContent(@Query("id") id : String): Flowable<Response<GuideContentDto>>

    @GET("/tiers/v1/get")
    fun getTierList(@Query("name") name : String): Flowable<Response<List<TierDto>>>

}

