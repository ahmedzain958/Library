package com.zainco.library.workmanager.codelab

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.zainco.library.R


class BlurWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    private val TAG = BlurWorker::class.java.simpleName

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun doWork(): Result {
        try {
            val resourceURI = inputData.getString(Constants.KEY_IMAGE_URI)
            val resolver = applicationContext.contentResolver
            // Create a bitmap from a test image in drawable resource
            val bitmap = BitmapFactory.decodeStream(
                resolver.openInputStream(Uri.parse(resourceURI))
            )

            //blurr the image bitmap and returns another bimtap
            val output: Bitmap = WorkerUtils.blurBitmap(bitmap, applicationContext)
            val outputUri: Uri = WorkerUtils.writeBitmapToFile(applicationContext, output)
            WorkerUtils.makeStatusNotification(
                "Output is "
                        + outputUri.toString(), applicationContext
            )
            // Return the output for the temp file
            val outputData = Data.Builder().putString(
                Constants.KEY_IMAGE_URI, outputUri.toString()
            ).build()
            return Result.success(outputData);
        } catch (throwable: Throwable) {
            // Technically WorkManager will return Result.failure()
            // but it's best to be explicit about it.
            // Thus if there were errors, we're return FAILURE
            Log.e(TAG, "Error applying blur", throwable);
            return Result.failure();
        }
    }
}