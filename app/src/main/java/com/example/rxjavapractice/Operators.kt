package com.example.rxjavapractice

import android.util.Log
import com.example.rxjavapractice.data.User
import com.example.rxjavapractice.data.UserProfile
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
val mUserList = mutableListOf<User>(
    User(1, "demo1", 12),
    User(2, "demo2", 15),
    User(3, "demo3", 23),
    User(4, "demo4", 12),
    User(5, "demo5", 23),
    User(6, "demo6", 16),
    User(7, "demo7", 23),
    User(8, "demo8", 16),
    User(9, "demo9", 16)
)
val mUserProfileList = mutableListOf<UserProfile>(
    UserProfile(1, "demo1", 12, "https://test.com/1"),
    UserProfile(2, "demo2", 15, "https://test.com/2"),
    UserProfile(3, "demo3", 23, "https://test.com/3"),
    UserProfile(4, "demo4", 12, "https://test.com/4"),
    UserProfile(5, "demo5", 23, "https://test.com/5"),
    UserProfile(6, "demo6", 16, "https://test.com/6"),
    UserProfile(7, "demo7", 23, "https://test.com/7"),
    UserProfile(8, "demo8", 16, "https://test.com/8"),
    UserProfile(9, "demo9", 16, "https://test.com/9"),
)
val mEmptyUserList = mutableListOf<User>()


fun justOperator() {
    val observable = Observable.just(mNumList, mNumList)
    val observer = object : Observer<List<Int>> {
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

fun filterOperator(): Observable<User> {
    return Observable.fromIterable(mUserList)
}

fun lastOperator(): Observable<User> {
    return Observable.fromIterable(mUserList)
}

fun lastOperatorEmptyList(): Observable<User> {
    return Observable.fromIterable(mEmptyUserList)
}

fun distinctOperator(): Observable<User> {
    return Observable.fromIterable(mUserList)
}

fun skipOperator(): Observable<User> {
    return Observable.fromIterable(mUserList)
}

fun bufferOperator(): Observable<User> {
    return Observable.fromIterable(mUserList)
}

fun mapOperator(): Observable<User> {
    return Observable.fromIterable(mUserList)
}

fun flatMapOperator(): Observable<User> {
    return Observable.fromIterable(mUserList)
}

fun getUserProfile(id: Long): Observable<UserProfile> {
    return Observable.fromIterable(mUserProfileList)
        .filter {
            it.id == id
        }
}

fun flatMapOperatorT2(): Observable<List<User>>{
    return Observable.just(mUserList)
}

fun groupByOperator(): Observable<User>{
    return Observable.fromIterable(mUserList)
}

