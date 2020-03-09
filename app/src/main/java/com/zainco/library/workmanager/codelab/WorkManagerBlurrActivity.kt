package com.zainco.library.workmanager.codelab

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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
        //get uri from SelectImageActivity
        val imageUriExtra = intent.getStringExtra(Constants.KEY_IMAGE_URI)
        viewModel.setImageUri(imageUriExtra)
        //display the image
        if (viewModel.getImageUri() != null) {
            Glide.with(this).load(viewModel.getImageUri()).into(image_view)
        }
        see_file_button.setOnClickListener {
            val currentUri: Uri = viewModel.getOutputUri()
            if (currentUri != null) {
                val actionView = Intent(Intent.ACTION_VIEW, currentUri)
                if (actionView.resolveActivity(packageManager) != null) {
                    startActivity(actionView)
                }
            }
        }
        cancel_button.setOnClickListener {
            viewModel.cancelWork()
        }

        // Show work status
        viewModel.getOutputWorkInfoForSavedData().observe(this, Observer { listOfWorkInfo ->
            // Note that these next few lines grab a single WorkInfo if it exists
            // This code could be in a Transformation in the ViewModel; they are included here
            // so that the entire process of displaying a WorkInfo is in one location.

            // If there are no matching work info, do nothing


            // Note that these next few lines grab a single WorkInfo if it exists
            // This code could be in a Transformation in the ViewModel; they are included here
            // so that the entire process of displaying a WorkInfo is in one location.

            // If there are no matching work info, do nothing
            if (!(listOfWorkInfo == null || listOfWorkInfo.isEmpty())) {
                // We only care about the one output status.
                // Every continuation has only one worker tagged TAG_OUTPUT
                val workInfo = listOfWorkInfo[0]

                val finished = workInfo.state.isFinished
                if (!finished) {
                    showWorkInProgress()
                } else {
                    showWorkFinished()
                    // Normally this processing, which is not directly related to drawing views on
                    // screen would be in the ViewModel. For simplicity we are keeping it here.
                    val outputData =
                        workInfo.outputData // gotten data when you put it inside the Result.success(outputData)
                    val outputImageUri = outputData.getString(Constants.KEY_IMAGE_URI)

                    // If there is an output file show "See File" button
                    if (!TextUtils.isEmpty(outputImageUri)) {
                        viewModel.setOutputUri(outputImageUri!!)
                        see_file_button.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    /**
     * Shows and hides views for when the Activity is processing an image
     */
    fun showWorkInProgress() {
        progress_bar.setVisibility(View.VISIBLE)
        cancel_button.setVisibility(View.VISIBLE)
        go_button.setVisibility(View.GONE)
        see_file_button.setVisibility(View.GONE)
    }

    /**
     * Shows and hides views for when the Activity is done processing an image
     */
    fun showWorkFinished() {
        progress_bar.setVisibility(View.GONE)
        cancel_button.setVisibility(View.GONE)
        go_button.setVisibility(View.VISIBLE)
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
