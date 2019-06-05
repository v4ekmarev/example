package com.vladlen.dotaautochessguide.screens.guides

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vladlen.dotaautochessguide.model.vo.guide.GuideItemVo
import com.vladlen.dotaautochessguide.usecases.guides.GetGuidesItemUseCase
import com.vladlen.dotaautochessguide.viewobjects.ViewObject
import com.vladlen.dotaautochessguide.viewobjects.mappersvo.GuidItemVoMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GuidesViewModel(private val getGuidesListUseCase: GetGuidesItemUseCase) : ViewModel(), GuidesContract {

    private var guideItemListLiveData: MutableLiveData<ViewObject<MutableList<GuideItemVo>>> = MutableLiveData()
    private val disposable: CompositeDisposable = CompositeDisposable()
    override val getGuideList: LiveData<ViewObject<MutableList<GuideItemVo>>>
        get() = guideItemListLiveData


    fun runGetGuideItemList() {
        guideItemListLiveData.value = ViewObject.loading()
        disposable.add(getGuidesListUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    return@map GuidItemVoMapper.fromDto(it)
                }
                .subscribe({ t ->
                    guideItemListLiveData.value = ViewObject.success(t)
                }, { t ->
                    guideItemListLiveData.value = ViewObject.error(t)
                }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}