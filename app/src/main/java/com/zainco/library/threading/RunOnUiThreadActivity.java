package com.zainco.library.threading;

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
//                runThread();
                runThreadUsingHandler();
            }
        });
    }

    private void runThreadUsingHandler() {

    }

    class ExampleRunnable implements Runnable {
        @Override
        public void run() {
            while (i++ < 1000) {
                if (i == 5)
                    btn.setText("5 sec");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //without synchronized the button text could be 1007
    private synchronized void runThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (i++ < 1000) {
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
        //the prev instead of this
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
