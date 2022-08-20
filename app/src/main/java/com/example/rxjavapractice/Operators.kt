package com.example.rxjavapractice

import android.util.Log
import com.example.rxjavapractice.data.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.Exception
import java.util.*
import java.util.concurrent.TimeUnit

val mNumList = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
val arraysNum1 = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
val arraysNum2 = arrayOf(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120)
val userList = mutableListOf<User>(
    User(1, "demo1", 12),
    User(2, "demo2", 15),
    User(3, "demo3", 16)
)


fun justOperator() {
    val observable = Observable.just(mNumList, mNumList)
    val observer = object : Observer<List<Int>> {
        //            override fun onSubscribe(d: Disposable) {
//                Log.d(TAG, "onSubscribe")
//            }
//
//            override fun onNext(t: Int) {
//                Log.d(TAG, "onNext $t")
//            }
//
//            override fun onError(e: Throwable) {
//                Log.d(TAG, "onError ${e.toString()}")
//            }
//
//            override fun onComplete() {
//                Log.d(TAG, "onComplete")
//            }
        override fun onSubscribe(d: Disposable) {
            Log.d(MainActivity.TAG, "onSubscribe")
        }

        override fun onNext(t: List<Int>) {
            Log.d(MainActivity.TAG, "onNext $t")
        }

        override fun onError(e: Throwable) {
            Log.d(MainActivity.TAG, "onError ${e.toString()}")
        }

        override fun onComplete() {
            Log.d(MainActivity.TAG, "onComplete")
        }
    }
    observable.subscribe(observer)
}

fun fromOperator() {
    val observable = Observable.fromArray(arraysNum1, arraysNum2)

    val observer = object : Observer<Array<Int>> {
        override fun onSubscribe(d: Disposable) {
            Log.d(MainActivity.TAG, "onSubscribe")
        }

        override fun onNext(t: Array<Int>) {
            Log.d(MainActivity.TAG, "onNext: ${Arrays.toString(t)}")
        }

        override fun onError(e: Throwable) {
            Log.d(MainActivity.TAG, "onError: ${e.toString()}")
        }

        override fun onComplete() {
            Log.d(MainActivity.TAG, "onComplete")
        }

    }
    observable.subscribe(observer)
}

fun fromIterableOperator() {
    val observable = Observable.fromIterable(mNumList)

    val observer = object : Observer<Int> {
        override fun onSubscribe(d: Disposable) {
            Log.d(MainActivity.TAG, "onSubscribe")
        }

        override fun onNext(t: Int?) {
            Log.d(MainActivity.TAG, "onNext: $t")
        }

        override fun onError(e: Throwable) {
            Log.d(MainActivity.TAG, "onError: ${e.toString()}")
        }

        override fun onComplete() {
            Log.d(MainActivity.TAG, "onComplete")
        }

    }

    observable.subscribe(observer)
}

fun rangeOperator(): Observable<Int> {
    return Observable.range(1, 14)
}

fun repeatOperator(): Observable<Int> {
    return Observable.range(1, 14).repeat(2)
}

fun intervalOperator(): Observable<Long> {
    return Observable.interval(1, TimeUnit.SECONDS).takeWhile { value ->
        value <= 10
    }
}

fun timerOperator(): Observable<Long> {
    return Observable.timer(5, TimeUnit.SECONDS)
}

fun createOperator(): Observable<Int> {
    return Observable.create(ObservableOnSubscribe<Int> {

        try {
            for (i in mNumList) {
                it.onNext(i * 5)
            }
            it.onComplete()

        } catch (e: Exception) {
            it.onError(e)
        }
    })
}

