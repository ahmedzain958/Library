package com.zainco.library.callrecord;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

/**
 * Created by irshadvali on 14/12/17.
 */

public class RecordingService extends Service {
    private MediaRecorder rec;
    private boolean recoderstarted;
    private File file;
    String path = "sdcard/alarms/";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private void startRecording() throws IOException {
        ContentValues values = new ContentValues(4);
        values.put(MediaStore.Audio.Media.TITLE, "zain");
        values.put(MediaStore.Audio.Media.DATE_ADDED, (int) (System.currentTimeMillis() / 1000));
        values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/mp3");
        values.put(MediaStore.Audio.Media.RELATIVE_PATH, "Music/Recordings/");

        Uri audiouri= getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values);
        ParcelFileDescriptor file = getContentResolver().openFileDescriptor(audiouri, "w");

        if (file != null) {
            MediaRecorder     audioRecorder = new MediaRecorder();
            audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            audioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            audioRecorder.setOutputFile(file.getFileDescriptor());
            audioRecorder.setAudioChannels(1);
            audioRecorder.setAudioChannels(1);
            audioRecorder.prepare();
            audioRecorder.start();
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            try {
                startRecording();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file = getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        }
        Date date = new Date();
        String stringDate = DateFormat.getDateTimeInstance().format(date);
        rec = new MediaRecorder();
        rec.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
        rec.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        rec.setOutputFile(file.getAbsoluteFile() + "/" + stringDate + "callrec.3gp");
        rec.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        TelephonyManager manager = (TelephonyManager) getApplicationContext().getSystemService(getApplicationContext().TELEPHONY_SERVICE);
        manager.listen(new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                if (TelephonyManager.CALL_STATE_IDLE == state) {
                    try {
                        if (rec == null) {
                            rec.stop();
                            rec.reset();
                            rec.release();
                            recoderstarted = false;
                            rec = null;
                            stopSelf();
                            System.out.println("IRSHAD CALL_STATE_IDLE");
                        } else {
                            rec.stop();
                            rec.reset();
                            rec.release();
                            recoderstarted = false;
                            rec = null;
                            stopSelf();
                            System.out.println("IRSHAD CALL_STATE_IDLE");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                    System.out.println("IRSHAD CALL_STATE_OFFHOOK");
                    try {
                        file = getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                        Date date = new Date();
                        String stringDate = DateFormat.getDateTimeInstance().format(date);
                        rec = new MediaRecorder();
                        rec.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
                        rec.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        rec.setOutputFile(file.getAbsoluteFile() + "/" + stringDate + "callrec.3gp");
                        rec.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                        TelephonyManager manager = (TelephonyManager) getApplicationContext().getSystemService(getApplicationContext().TELEPHONY_SERVICE);
                        rec.prepare();
                        rec.start();
                        recoderstarted = true;
                    } catch (IOException e) {
                        System.out.println("irshad Exception =" + e.getMessage());
                        e.printStackTrace();
                    }
                }


            }
        }, PhoneStateListener.LISTEN_CALL_STATE);

        return START_STICKY;
    }
}
