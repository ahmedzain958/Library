package com.zainco.library.rxjava2.coldobservables

import android.os.Build
import androidx.annotation.RequiresApi
import io.reactivex.Observable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

/*Cold Observables are those which emits all the items whenever we subscribe to them. Meaning,
if two observers are subscribed to the same observable at different time, they will receive all the emissions from that observable.
 Its like playing a recorded mp3 song from a device, no matter when we play (subscribe) it we can hear the entire song all the time.
Example
Observable<String> source = Observable.just("Value1", "Value2", "Value3");
source.subscribe(s -> System.out.println("Observer1: "+s));
source.subscribe(s -> System.out.println("Observer2: "+s));
Output
Observer1: Value1
Observer1: Value2
Observer1: Value3
Observer2: Value1
Observer2: Value2
Observer2: Value3
As you can see from the above output both the observers receive all the items (Value1, Value2, Value3) emitted from the observable.

 */
/*

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    val myObservable: Observable<Long> = Observable.interval(2, TimeUnit.SECONDS)
    myObservable.subscribe { item: Long ->
        println(
            "Observer 1: ${item + 1} ------" + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern(
                    "ss"
                )
            )
        )
    }
    Thread.sleep(2000)
    myObservable.subscribe { item: Long ->
        println(
            "Observer 2: ${item + 1} ------" + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern(
                    "ss"
                )
            )
        )
    }
    Thread.sleep(4000)
    println("---------------------------")
    */
/*
    Observer 1: 1 ------59
Observer 1: 2 ------01 difference is 2 secs
Observer 2: 1 ------01
Observer 1: 3 ------03
---------------------------
Observer 2: 2 ------03
     *//*

}
*/

@RequiresApi(Build.VERSION_CODES.O)
fun main(args: Array<String>) {
    val myObservable: Observable<Long> = Observable.interval(1, TimeUnit.SECONDS)
    myObservable.subscribe { item: Long ->
        println(
            "Observer 1: ${item + 1} ------" + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("ss"))
        )
    }
    Thread.sleep(2000)//after this line the code won't execute until the 2secs passes
    myObservable.subscribe { item: Long ->
        println(
            "Observer 2: ${item + 1} ------" + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("ss"))
        )
    }
    Thread.sleep(4000)
    println("---------------------------")
}
/*
Observer 1: 1 ------18
Observer 1: 2 ------19
Observer 1: 3 ------20//has just subscribed in the 1st second of the 4 secs and instantly after the sleeping 2 secs
Observer 2: 1 ------20//has just subscribed in the 1st second of the 4 secs and instantly after the sleeping 2 secs
Observer 1: 4 ------21
Observer 2: 2 ------21
Observer 1: 5 ------22
Observer 2: 3 ------22
Observer 1: 6 ------23
---------------------------
Observer 2: 4 ------23


*/
