package com.zainco.library.threading.codinginflow;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.zainco.library.R;

public class HandlerActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button buttonStartThread;

    /*responsibility of handler is getting the work in the message queue
    and works with the thread in which it has been instantiated in here for ex: ( main thread)
    which means we can use it to get our work to the main thread
    * */
    private Handler mainHandler = new Handler();
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
                // method(1) handler
                Handler handler = new Handler(Looper.getMainLooper());
                /*this line will lead to crash in
                handlerWithoutLooper.post(new Runnable() {
                 can't create handler inside thread that hasn't called Looper.prepare() because the handler can only work iff we didn't pass a constructor param Looper.getMainLooper() to the handler*/


                if (i == 5) {
                    handler.post(new Runnable() {

                        /*for the thread that only has a looper and a msg queue(here main thread)*/
//                    mainHandler.post(new Runnable() {//runnable itself doesn't start a new thread instead runnable is a package of work to be done
                        @Override
                        public void run() {
                            buttonStartThread.setText("asssssssssssssssssssssssssssssssssssssssssssssssasasas");
                        }
                    });
                    /////////////////////////////////////
                                   /*
                  method(2) post on button
                  instead of using the upper looper we can also make
                   * */
                    buttonStartThread.post(new Runnable() {
                        @Override
                        public void run() {
                            buttonStartThread.setText("asssssssssssssssssssssssssssssssssssssssssssssssasasas");
                        }
                    });
                    /////////////////////////////////////
    /*
                  method(3) use runOnUIThread() like Handler()
                                 * */
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            buttonStartThread.setText("asssssssssssssssssssssssssssssssssssssssssssssssasasas");
                        }
                    });


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
