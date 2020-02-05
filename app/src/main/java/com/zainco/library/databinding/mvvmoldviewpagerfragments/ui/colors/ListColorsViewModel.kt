package com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.colors

import com.zainco.library.databinding.mvvmoldviewpagerfragments.domain.model.Color
import com.zainco.library.databinding.mvvmoldviewpagerfragments.domain.repository.ColorRepository
import com.zainco.library.databinding.mvvmoldviewpagerfragments.remote.response.Response
import com.zainco.library.databinding.mvvmoldviewpagerfragments.remote.response.Status
import com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.base.BaseViewModel
import com.zainco.library.databinding.mvvmoldviewpagerfragments.util.schedulers.BaseScheduler
import javax.inject.Inject

/**
 * @author Wellington Costa on 31/12/2017.
 */
class ListColorsViewModel
@Inject constructor(
    private val scheduler: BaseScheduler,
    private val colorRepository: ColorRepository
) : BaseViewModel<List<Color>>() {

    override fun loadData() {
        colorRepository.getColors()
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .doOnSubscribe { loadingStatus.setValue(true) }
            .doAfterTerminate { loadingStatus.setValue(false) }
            .subscribe({ result ->
                response.setValue(Response(Status.SUCCESS, result.data, null))
            }, { throwable ->
                response.setValue(Response(Status.ERROR, null, throwable))
            })
    }

}