package com.zainco.library.threading;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.zainco.library.R;

public class ThreadActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button buttonStartThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        buttonStartThread = findViewById(R.id.button_start_thread);
        /*
        * instead of this in ui thread we used another worker thread because if we clicked on the switch it will
        *  crash without sleeping in new worker thread
        *
        * for (int i = 0; i < 10; i++) {
                try {
                * //this sleep is in ui thread it gives ===> ANR
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            *
        * */
    }

    class ExampleThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
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
        //method 1
//        new ExampleThread().start();
        //method 2
        new Thread(new ExampleRunnable()).start();
        // in both methods we can mske long running operations(loops and sleep) and we can interact with UI thread views without exceptions
    }

    public void stopThread(View view) {
    }
}
