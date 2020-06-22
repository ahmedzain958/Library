package com.zainco.library.git

import androidx.lifecycle.ViewModel
import com.zainco.daggerpractice.inflow.MainActivity

/**
 * Created by Ahmed Zain on 6/21/2020.
 */
class Album(val Main: MainActivity) : ViewModel() {
    var albums: List<Album> = listOf()
}