package com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.users

import com.zainco.library.databinding.mvvmoldviewpagerfragments.domain.model.User
import com.zainco.library.databinding.mvvmoldviewpagerfragments.domain.repository.UserRepository
import com.zainco.library.databinding.mvvmoldviewpagerfragments.remote.response.Response
import com.zainco.library.databinding.mvvmoldviewpagerfragments.remote.response.Status
import com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.base.BaseViewModel
import com.zainco.library.databinding.mvvmoldviewpagerfragments.util.schedulers.BaseScheduler
import javax.inject.Inject

/**
 * @author Wellington Costa on 31/12/2017.
 */
class ListUsersViewModel
@Inject constructor(
    private val scheduler: BaseScheduler,
    private val userRepository: UserRepository
) : BaseViewModel<List<User>>() {

    override fun loadData() {
        userRepository.getUsers()
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