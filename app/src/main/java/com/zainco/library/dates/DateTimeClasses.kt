package com.zainco.library.dates

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by Ahmed Zain on 6/22/2020.
 */
class DateTimeClasses {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun displayCurrentSecond(): String {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern("ss"))
        }
    }

}