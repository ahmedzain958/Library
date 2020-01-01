package com.zainco.library.databinding.ex1

import android.view.View
import android.widget.Toast
import com.zainco.library.databinding.ex1.MainApplication.Companion.applicationContext


class MyClickHandlers {
    fun onFabClicked(view: View) {
        Toast.makeText(
            applicationContext(),
            "FAB clicked!",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun onButtonClick(view: View) {
        Toast.makeText(
            applicationContext(),
            "Button clicked!",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun onButtonClickWithParam(
        view: View,
        user: User
    ) {
        user.name = "Zain"
        Toast.makeText(
            applicationContext(),
            "Button clicked! Name: " + user.name,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun onButtonLongPressed(view: View): Boolean {
        Toast.makeText(
            applicationContext(),
            "Button long pressed!",
            Toast.LENGTH_SHORT
        ).show()
        return false
    }

}