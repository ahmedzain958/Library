package com.zainco.library.workmanager.codelab

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_work_manager_blurr.*

class WorkManagerBlurrActivity : AppCompatActivity() {
    lateinit var viewModel: BlurViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager_blurr)
        viewModel = ViewModelProvider(this).get(BlurViewModel::class.java)
        go_button.setOnClickListener {
            viewModel.applyBlurr(getBlurLevel())
        }
        val imageUriExtra = intent.getStringExtra(Constants.KEY_IMAGE_URI)
        viewModel.setImageUri(imageUriExtra)
        if (viewModel.getImageUri() != null) {
            Glide.with(this).load(viewModel.getImageUri()).into(image_view)
        }
    }
    private fun getBlurLevel(): Int {
        val radioGroup = findViewById<RadioGroup>(R.id.radio_blur_group)
        when (radioGroup.checkedRadioButtonId) {
            R.id.radio_blur_lv_1 -> return 1
            R.id.radio_blur_lv_2 -> return 2
            R.id.radio_blur_lv_3 -> return 3
        }
        return 1
    }
}
