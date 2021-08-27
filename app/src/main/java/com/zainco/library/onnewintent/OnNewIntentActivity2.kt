package com.zainco.library.onnewintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zainco.library.R

class OnNewIntentActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_new_intent2)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.getIntExtra("1", 0)?.let {
            Toast.makeText(this@OnNewIntentActivity2,
                intent.getIntExtra("1", 0),
                Toast.LENGTH_SHORT).show()
        }
    }
}
