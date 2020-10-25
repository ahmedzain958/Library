package com.zainco.library.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_test_coroutines.*
import kotlinx.coroutines.*
import timber.log.Timber

class TestCoroutinesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_coroutines)
        Timber.d("app started")
        GlobalScope.launch(Dispatchers.IO) {
            val answer = doNetworkCall()//this is ex makes a network call for 3 secs
            withContext(Dispatchers.Main) {
                //executed in the main thread
                textview.text = answer
            }
        }
        GlobalScope.launch(Dispatchers.IO) {
            val answer =
                doNetworkCall()//this is ex makes a network call for 3 secs in IO thread (bg thread)
            GlobalScope.launch(Dispatchers.Main) {
                textview2.text = answer
            }
        }

        GlobalScope.launch {//both answer will display after 6 seconds
            val doNetworkCall = doNetworkCall()//this will influence the second delay
            val doNetworkCall2 = doNetworkCall2()//will be affected by the first delay
            Timber.d(doNetworkCall)
            Timber.d(doNetworkCall2)
        }


        //runblocking vs Globalscope
        Timber.d("qq-before Globalscope.launch")
        GlobalScope.launch(Dispatchers.Main) {
            Timber.d("qq-start Globalscope.launch")
            delay(3000L)
            Timber.d("qq-end Globalscope.launch")
        }
        Timber.d("qq-after Globalscope.launch")
        runBlocking {
            Timber.d("qq-start  runblocking")
            delay(3000L)//this is like calling delay() method inside oncreate() (mean inside main thread) but it can't be done bec. it won't be inside coroutines
            Timber.d("qq-end  runblocking")
        }
        /*equals to
        oncreate(){
          Timber.d("qq-start")
            delay(3000L)
            Timber.d( "qq-end ")
         }
         */
        runBlocking {
            Timber.d("zz- start  inside runblocking")
            launch(Dispatchers.IO) {
                Timber.d("zz- start launch 1 inside runblocking")
                delay(3000L)
                Timber.d("zz- end launch 1 inside runblocking")
            }
            launch(Dispatchers.IO) {
                Timber.d("zz- start launch 2 inside runblocking")
                delay(3000L)
                Timber.d("zz- end launch 2 inside runblocking")
            }
            Timber.d("will be executed normally without blocking")
        }


        Timber.d("xx- before runblocking")
        runBlocking {
            Timber.d("xx- start  inside runblocking")
            launch(Dispatchers.IO) {
                Timber.d("xx- start launch 1 inside runblocking")
                delay(3000L)//this doesn't block the next  delay(3000L) inside the next launch
                Timber.d("xx- end launch 1 inside runblocking")
            }
            launch(Dispatchers.IO) {
                Timber.d("xx- start launch 2 inside runblocking")
                delay(3000L)
                Timber.d("xx- end launch 2 inside runblocking")
            }
            delay(1000L)
            Timber.d("xx- end inside runblocking")
        }
        Timber.d("xx- after runblocking")
        /*
2020-09-23 15:15:42.278 16180-16180/com.zainco.library D/TestCoroutinesActivity: xx- before runblocking
2020-09-23 15:15:42.280 16180-16180/com.zainco.library D/TestCoroutinesActivity$onCreate: xx- start  inside runblocking
2020-09-23 15:15:42.282 16180-16768/com.zainco.library D/TestCoroutinesActivity$onCreate: xx- start launch 1 inside runblocking
2020-09-23 15:15:42.283 16180-16768/com.zainco.library D/TestCoroutinesActivity$onCreate: xx- start launch 2 inside runblocking
2020-09-23 15:15:43.298 16180-16180/com.zainco.library D/TestCoroutinesActivity$onCreate: xx- end inside runblocking
2020-09-23 15:15:45.317 16180-16768/com.zainco.library D/TestCoroutinesActivity$onCreate: xx- end launch 2 inside runblocking
2020-09-23 15:15:45.318 16180-16784/com.zainco.library D/TestCoroutinesActivity$onCreate: xx- end launch 1 inside runblocking
2020-09-23 15:15:45.319 16180-16180/com.zainco.library D/TestCoroutinesActivity: xx- after runblocking
         */
        runBlocking {
            Timber.d("yy-start")
            delay(1000L)
            delay(1000L)
            Timber.d("yy-end")
            /*
2020-09-24 10:33:05.725 15436-15436/com.zainco.library D/TestCoroutinesActivity$onCreate: yy-start
2020-09-24 10:33:07.783 15436-15436/com.zainco.library D/TestCoroutinesActivity$onCreate: yy-end
             */
        }
    }

    suspend fun doNetworkCall(): String {
        delay(2000L)
        return "method doNetworkCall take some time to get the answer - this is the answer"
    }

    suspend fun doNetworkCall2(): String {
        delay(2000L)
        return "method doNetworkCall2 take some time to get the answer - this is the answer"
    }
}