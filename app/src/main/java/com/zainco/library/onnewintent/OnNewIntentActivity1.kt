package com.zainco.library.onnewintent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_on_new_intent1.*

class OnNewIntentActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_new_intent1)
        button6.setOnClickListener {
            startActivity(Intent(this@OnNewIntentActivity1,
                OnNewIntentActivity2::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .putExtra("1", 1))
        }
    }


}
