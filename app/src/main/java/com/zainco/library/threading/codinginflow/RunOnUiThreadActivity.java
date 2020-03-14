package com.zainco.library.threading.codinginflow;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.zainco.library.R;

public class RunOnUiThreadActivity extends Activity {

    Button btn;
    int i = 0;
    Handler mainHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runonuithread);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runThread();
            }
        });
    }

    //without synchronized the button text could be 1007 or any number more than 1000 if the button clicked multiple times
    //every time we click the button new worker thread is created (it can access i value) different from the previously created worker thread
    //, so synchronized solved this issue
    private synchronized void runThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (i++ < 1000) {
                    //switches easily between threads
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btn.setText("#" + i);
                        }
                    });
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        //the prev instead of this because run on ui thread wraps the dealing with the view not loops and somethings like that
       /* runOnUiThread(new Thread(new Runnable() {
            public void run() {
                while (i++ < 1000) {
                    btn.setText("#" + i);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }));*/
    }
}
