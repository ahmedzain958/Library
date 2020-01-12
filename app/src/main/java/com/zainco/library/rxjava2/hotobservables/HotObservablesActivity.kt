package com.zainco.library.rxjava2.hotobservables

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding.widget.RxTextView
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_hot_observables.*


class HotObservablesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hot_observables)
        RxTextView.textChanges(editTextHot)
            .subscribe { charSequence ->
                textViewHot1.text = charSequence.toString()
            }
        buttonHot2.setOnClickListener {
            RxTextView.textChanges(editTextHot)
                .subscribe { charSequence ->
                    textViewHot2.text = charSequence.toString()
                }
        }


    }
}
