/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zainco.library.workmanager.codelab;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.io.File;

/**
 * Cleans up temporary files generated during blurring process
 */
public class CleanupWorker extends Worker {

    /**
     * Creates an instance of the {@link Worker}.
     *
     * @param appContext   the application {@link Context}
     * @param workerParams the set of {@link WorkerParameters}
     */
    public CleanupWorker(@NonNull Context appContext, @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    private static final String TAG = CleanupWorker.class.getSimpleName();

    @NonNull
    @Override
    public Worker.Result doWork() {
        Context applicationContext = getApplicationContext();

        // Makes a notification when the work starts and slows down the work so that it's easier to
        // see each WorkRequest start, even on emulated devices
        WorkerUtils.makeStatusNotification("Cleaning up old temporary files",
                applicationContext);
        WorkerUtils.sleep();

        try {
            File outputDirectory = new File(applicationContext.getFilesDir(),
                    Constants.OUTPUT_PATH);
            if (outputDirectory.exists()) {
                File[] listFiles = outputDirectory.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    for (File file : listFiles) {
                        String name = file.getName();
                        if (!TextUtils.isEmpty(name) && name.endsWith(".png")) {
                            boolean deleted = file.delete();
                            Log.i(TAG, String.format("Deleted %s - %s", name, deleted));
                        }
                    }
                }
            }
            return Result.success();
        } catch (Exception exception) {
            Log.e(TAG, "Error cleaning up", exception);
            return Result.failure();
        }
    }
}
