package com.zainco.library.workmanager.codelab

import android.net.Uri
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.*
import com.zainco.library.workmanager.codelab.Constants.*


class BlurViewModel : ViewModel() {
    private lateinit var mOutputUri: Uri
    var workManager: WorkManager = WorkManager.getInstance()
    private var mImageUri: Uri? = null
    private val savedWorkInfo: LiveData<List<WorkInfo>> by lazy {
        workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)
    }

    fun applyBlurr(blurLevel: Int) {
        // Add WorkRequest to Cleanup temporary images
        var continuationWorkRequest: WorkContinuation = workManager
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
            val blurBuilderWorkRequest = blurBuilder.build()
            //neat feature of chaining is that the output of one WorkRequest becomes the input of the next WorkRequest in the chain
            continuationWorkRequest = continuationWorkRequest.then(blurBuilderWorkRequest)
        }
        // Create charging constraint
        var constraints: Constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()
        // Add WorkRequest to save the image to the filesystem
        val saveWorkRequest = OneTimeWorkRequest.Builder(SaveImageToFileWorker::class.java)
            .setConstraints(constraints)
            .addTag(TAG_OUTPUT)
            .build()
        continuationWorkRequest = continuationWorkRequest.then(saveWorkRequest)

        continuationWorkRequest.enqueue()
    }

    fun setImageUri(uri: String) {
        mImageUri = uriOrNull(uri)
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

    fun setOutputUri(outputImageUri: String) {
        mOutputUri = uriOrNull(outputImageUri)!!
    }

    fun getOutputUri(): Uri {
        return mOutputUri
    }

    fun getImageUri(): Uri? = mImageUri
    fun getOutputWorkInfoForSavedData(): LiveData<List<WorkInfo>> {
        return savedWorkInfo
    }

    fun cancelWork() {
        workManager.cancelUniqueWork(IMAGE_MANIPULATION_WORK_NAME)
    }

}