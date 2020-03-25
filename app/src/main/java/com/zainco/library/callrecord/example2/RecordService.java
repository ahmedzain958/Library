/* Copyright (c) 2012 Kobi Krasnoff
 *
 * This file is part of Call recorder For Android.
 *
 * Call recorder For Android is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Call recorder For Android is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Call recorder For Android.  If not, see <http://www.gnu.org/licenses/>
 */
package com.zainco.library.callrecord.example2;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.documentfile.provider.DocumentFile;

import com.zainco.library.R;

public class RecordService extends Service {
    private MediaRecorder recorder;
    private String phoneNumber;

    private DocumentFile file;
    private boolean onCall = false;
    private boolean recording = false;
    private boolean onForeground = false;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        UserPreferences.init(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Constants.TAG, "RecordService onStartCommand");
        if (intent == null)
            return START_NOT_STICKY;

        int commandType = intent.getIntExtra("commandType", 0);
        if (commandType == 0)
            return START_NOT_STICKY;

        boolean enabled = UserPreferences.getEnabled();

        switch (commandType) {
            case Constants.RECORDING_ENABLED:
                Log.d(Constants.TAG, "RecordService RECORDING_ENABLED");
                if (enabled && phoneNumber != null && onCall && !recording) {
                    Log.d(Constants.TAG, "RecordService STATE_START_RECORDING");
                    startService();
                    startRecording();
                }
                break;
            case Constants.RECORDING_DISABLED:
                Log.d(Constants.TAG, "RecordService RECORDING_DISABLED");
                if (onCall && phoneNumber != null && recording) {
                    Log.d(Constants.TAG, "RecordService STATE_STOP_RECORDING");
                    stopAndReleaseRecorder();
                    recording = false;
                }
                break;
            case Constants.STATE_INCOMING_NUMBER:
                Log.d(Constants.TAG, "RecordService STATE_INCOMING_NUMBER");
                startService();
                if (phoneNumber == null)
                    phoneNumber = intent.getStringExtra("phoneNumber");
                break;
            case Constants.STATE_CALL_START:
                Log.d(Constants.TAG, "RecordService STATE_CALL_START");
                onCall = true;

                if (enabled && phoneNumber != null && !recording) {
                    startService();
                    startRecording();
                }
                break;
            case Constants.STATE_CALL_END:
                Log.d(Constants.TAG, "RecordService STATE_CALL_END");
                onCall = false;
                phoneNumber = null;
                stopAndReleaseRecorder();
                recording = false;
                stopService();
                break;
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(Constants.TAG, "RecordService onDestroy");
        stopAndReleaseRecorder();
        stopService();
        super.onDestroy();
    }

    /// In case it is impossible to record
    private void terminateAndEraseFile() {
        Log.d(Constants.TAG, "RecordService terminateAndEraseFile");
        stopAndReleaseRecorder();
        recording = false;
        if (file != null)
            deleteFile();
    }

    private void stopService() {
        Log.d(Constants.TAG, "RecordService stopService");
        stopForeground(true);
        onForeground = false;
        this.stopSelf();
    }

    private void deleteFile() {
        Log.d(Constants.TAG, "RecordService deleteFile");
        file.delete();
        file = null;
    }

    private void stopAndReleaseRecorder() {
        if (recorder == null)
            return;
        Log.d(Constants.TAG, "RecordService stopAndReleaseRecorder");
        boolean recorderStopped = false;
        boolean exception = false;

        try {
            recorder.stop();
            recorderStopped = true;
        } catch (IllegalStateException e) {
            Log.e(Constants.TAG, "Failed to stop recorder.  Perhaps it wasn't started?", e);
            exception = true;
        }
        recorder.reset();
        recorder.release();
        recorder = null;
        if (exception) {
            deleteFile();
        }
        if (recorderStopped) {
            Toast.makeText(this, this.getString(R.string.receiver_end_call),
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void startRecording() {
        Log.d(Constants.TAG, "RecordService startRecording");
        recorder = new MediaRecorder();

        try {
            recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            file = FileHelper.getFile(this, phoneNumber);
            ParcelFileDescriptor fd = getContentResolver()
                    .openFileDescriptor(file.getUri(), "w");
            if (fd == null)
                throw new Exception("Failed open recording file.");
            recorder.setOutputFile(fd.getFileDescriptor());

            recorder.setOnErrorListener((mr, what, extra) -> {
                Log.e(Constants.TAG, "OnErrorListener " + what + "," + extra);
                terminateAndEraseFile();
            });

            recorder.setOnInfoListener((mr, what, extra) -> {
                Log.e(Constants.TAG, "OnInfoListener " + what + "," + extra);
                terminateAndEraseFile();
            });

            recorder.prepare();

            // Sometimes the recorder takes a while to start up
            Thread.sleep(2000);

            recorder.start();
            recording = true;

            Log.d(Constants.TAG, "RecordService: Recorder started!");
            Toast toast = Toast.makeText(this,
                    this.getString(R.string.receiver_start_call),
                    Toast.LENGTH_SHORT);
            toast.show();
        } catch (Exception e) {
            Log.e(Constants.TAG, "Failed to set up recorder.", e);
            terminateAndEraseFile();
            Toast toast = Toast.makeText(this,
                    this.getString(R.string.record_impossible),
                    Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void startService() {
        if (onForeground)
            return;

        Log.d(Constants.TAG, "RecordService startService");
        Intent intent = new Intent(this, CallRecorderExample2MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                getBaseContext(), 0, intent, 0);

        Notification notification = new NotificationCompat.Builder(
                getBaseContext())
                .setContentTitle(this.getString(R.string.notification_title))
                .setTicker(this.getString(R.string.notification_ticker))
                .setContentText(this.getString(R.string.notification_text))
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .build();

        notification.flags = Notification.FLAG_NO_CLEAR;

        startForeground(0, notification);
        onForeground = true;
    }
}
