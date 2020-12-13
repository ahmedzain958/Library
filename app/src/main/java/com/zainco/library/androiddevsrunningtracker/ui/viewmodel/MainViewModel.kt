package com.zainco.library.androiddevsrunningtracker.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zainco.library.androiddevsrunningtracker.db.Run
import com.zainco.library.androiddevsrunningtracker.repository.MainRepository
import kotlinx.coroutines.launch

/**
 * Created by Ahmed Zain on 8/29/2020.
 */
class MainViewModel /*instead of pervious viewmodel factory*/
@ViewModelInject constructor(val mainRepository: MainRepository) : ViewModel() {
    fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }
}