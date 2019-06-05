package com.vladlen.dotaautochessguide.screens.guides.guidcontent

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vladlen.dotaautochessguide.model.vo.guide.GuideContentVo
import com.vladlen.dotaautochessguide.usecases.guides.GetGuideContentUseCase
import com.vladlen.dotaautochessguide.viewobjects.ViewObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GuideContentViewModel(private val getGuideContentUseCase: GetGuideContentUseCase) : ViewModel(), GuideContentContract {

    private var guideContentLiveData: MutableLiveData<ViewObject<GuideContentVo>> = MutableLiveData()
    private val disposable: CompositeDisposable = CompositeDisposable()
    override val getGuideContent: LiveData<ViewObject<GuideContentVo>>
        get() = guideContentLiveData


    fun runGetGuideContent(id: String) {
        guideContentLiveData.value = ViewObject.loading()
        disposable.add(getGuideContentUseCase.execute(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ t ->
                guideContentLiveData.value = ViewObject.success(t)
            }, { t ->
                guideContentLiveData.value = ViewObject.error(t)
            }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}