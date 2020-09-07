package com.zainco.library.androiddevsrunningtracker.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.zainco.library.androiddevsrunningtracker.repository.MainRepository

/**
 * Created by Ahmed Zain on 8/29/2020.
 */
class MainViewModel /*instead of pervious viewmodel factory*/
@ViewModelInject constructor(val repository: MainRepository) : ViewModel()