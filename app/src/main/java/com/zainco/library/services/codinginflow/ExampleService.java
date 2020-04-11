package com.zainco.library.services.codinginflow;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.zainco.library.R;

import static com.zainco.library.services.codinginflow.ForegroundServiceActivity.CHANNEL_ID;

public class ExampleService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");

        Intent notificationIntent = new Intent(this, ForegroundServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this,/*is mandatory for android O*/ CHANNEL_ID)
                //notification title
                .setContentTitle("Example Service")
                //notification text
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_bookmark_remove)
                //to open this activity for this pending intent
                .setContentIntent(pendingIntent)
                .build();
//normally to start a notification we call notificationManager.notify() but here it the service starts it itself
        //if startForeground hasn't called before 5 secs the service will be stopped
        startForeground(1, notification);

        //do heavy work on a background thread
        //stopSelf();

        return START_NOT_STICKY;
        /*
        START_STICKY :if android system terminates service restarted again but the re-sent intent = null
        START_REDELIVER_INTENT : restarted with the same previous intent
        START_NOT_STICKY :service not restarted
        */
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
