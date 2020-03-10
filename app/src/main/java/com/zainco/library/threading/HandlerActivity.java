package com.zainco.library.threading;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.zainco.library.R;

public class HandlerActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button buttonStartThread;
    /*responsibility of handler is getting the work in the message queue
    and works with the thread inwhich it has been instantiated in here for ex: it will work with the main thread
    which means we can use it to get our work to the main thread
    * */

    private Handler handler = new Handler();
/*
every click on button or switch is added to the message queue and executed sequentially
the Looper loops through the message queue

* */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        buttonStartThread = findViewById(R.id.button_start_thread);

    }


    //another method to create a worker thread
    class ExampleRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                // the next line gives
                // android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
                //this means that ui widgets aren't thread safe; we can't access them from another thread
                //be to access views from the background thread we need Handler
                if (i == 5) {
                    buttonStartThread.setText("asssssssssssssssssssssssssssssssssssssssssssssssasasas");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void startThread(View view) {
        new Thread(new ExampleRunnable()).start();
    }

    public void stopThread(View view) {
    }
}
