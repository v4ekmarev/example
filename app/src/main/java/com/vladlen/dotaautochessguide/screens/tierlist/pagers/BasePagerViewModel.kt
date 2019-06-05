package com.vladlen.dotaautochessguide.screens.tierlist.pagers

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vladlen.dotaautochessguide.model.vo.tier.TierVo
import com.vladlen.dotaautochessguide.usecases.tierlist.GetTierListUseCase
import com.vladlen.dotaautochessguide.viewobjects.ViewObject
import com.vladlen.dotaautochessguide.viewobjects.mappersvo.tier.TierVoMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

open class BasePagerViewModel(private val getTierListUseCase: GetTierListUseCase) : ViewModel(), BasePagerContract {
    private var guideTierListLiveData: MutableLiveData<ViewObject<MutableList<TierVo>>> = MutableLiveData()


    override val getTierList: LiveData<ViewObject<MutableList<TierVo>>>
        get() = guideTierListLiveData

    protected val disposable: CompositeDisposable = CompositeDisposable()

    fun runGetTier(name: String) {
        guideTierListLiveData.value = ViewObject.loading()
        disposable.add(getTierListUseCase.execute(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    return@map TierVoMapper.fromDto(it)
                }
                .subscribe({ t ->
                    guideTierListLiveData.value = ViewObject.success(t)
                }, { t ->
                    guideTierListLiveData.value = ViewObject.error(t)
                }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}