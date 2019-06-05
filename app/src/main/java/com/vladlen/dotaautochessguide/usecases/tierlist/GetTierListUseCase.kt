package com.vladlen.dotaautochessguide.usecases.tierlist

import com.vladlen.dotaautochessguide.application.rest.RestClient
import com.vladlen.dotaautochessguide.model.dto.tier.TierDto
import com.vladlen.dotaautochessguide.usecases.BaseUseCase
import io.reactivex.Flowable

class GetTierListUseCase(private val restClient: RestClient) : BaseUseCase<List<TierDto>>() {
    private lateinit var name : String
    override fun buildUseCaseObservable(): Flowable<List<TierDto>> {
        return restClient.getTierList(name).map { response ->
            return@map response.data
        }
    }

    fun execute(name: String): Flowable<List<TierDto>> {
        this.name = name
        return execute()
    }
}