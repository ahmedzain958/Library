package com.zainco.library.workmanager.codelab

import android.net.Uri
import android.text.TextUtils
import androidx.lifecycle.ViewModel
import androidx.work.*
import com.zainco.library.workmanager.codelab.Constants.IMAGE_MANIPULATION_WORK_NAME
import com.zainco.library.workmanager.codelab.Constants.KEY_IMAGE_URI


class BlurViewModel : ViewModel() {
    var workManager: WorkManager = WorkManager.getInstance()
    private var mImageUri: Uri? = null

    fun applyBlurr(blurLevel: Int) {
        // Add WorkRequest to Cleanup temporary images
        var continuation: WorkContinuation = workManager
            .beginUniqueWork(
                IMAGE_MANIPULATION_WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequest.from(CleanupWorker::class.java)
            )
        // Add WorkRequests to blur the image the number of times requested
        for (i in 0 until blurLevel) {
            val blurBuilder =
                OneTimeWorkRequest.Builder(BlurWorker::class.java)
            // Input the Uri if this is the first blur operation
            // After the first blur operation the input will be the output of previous
            // blur operations.
            if (i == 0) {
                blurBuilder.setInputData(createInputDataForURI())
            }
            //neat feature of chaining is that the output of one WorkRequest becomes the input of the next WorkRequest in the chain
            continuation = continuation.then(blurBuilder.build())
        }
        // Add WorkRequest to save the image to the filesystem
        // Add WorkRequest to save the image to the filesystem
        val save = OneTimeWorkRequest.Builder(SaveImageToFileWorker::class.java)
            .build()
        continuation = continuation.then(save)

        continuation.enqueue()
    }

    fun setImageUri(uri: String?) {
        mImageUri = uriOrNull(uri!!)
    }

    private fun uriOrNull(uriString: String): Uri? {
        return if (!TextUtils.isEmpty(uriString)) {
            Uri.parse(uriString)
        } else null
    }

    fun createInputDataForURI(): Data {
        val builder = Data.Builder()
        if (mImageUri != null) {
            builder.putString(KEY_IMAGE_URI, mImageUri.toString())
        }
        return builder.build()
    }

    fun getImageUri(): Uri? = mImageUri
}