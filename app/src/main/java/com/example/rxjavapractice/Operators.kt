package com.example.rxjavapractice

import android.util.Log
import com.example.rxjavapractice.data.Blog
import com.example.rxjavapractice.data.BlogDetail
import com.example.rxjavapractice.data.User
import com.example.rxjavapractice.data.UserProfile
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
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

val mBlogList = mutableListOf<Blog>(
    Blog(1, 2, "title1", "content1"),
    Blog(2, 3, "title2", "content2"),
    Blog(3, 4, "title2", "content2"),
    Blog(4, 9, "title1", "content1"),
    Blog(5, 10, "title3", "content3"),
    Blog(6, 11, "title1", "content1"),
    Blog(7, 3, "title3", "content3"),
    Blog(8, 2, "title1", "content1"),
    Blog(9, 6, "title4", "content4"),
    Blog(10, 13, "title1", "content1"),
    Blog(11, 1, "title4", "content4"),
)


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

fun getUserProfileById(id: Long): Observable<UserProfile> {
    return Observable.fromIterable(mUserProfileList)
        .filter {
            it.id == id
        }
}

fun flatMapOperatorT2(): Observable<List<User>> {
    return Observable.just(mUserList)
}

fun groupByOperator(): Observable<User> {
    return Observable.fromIterable(mUserList)
}

fun getUser(): Observable<User> {
    return Observable.fromIterable(mUserList)
}

fun getUserProfile(): Observable<UserProfile> {
    return Observable.fromIterable(mUserProfileList)
}

fun mergeOperator(): Observable<Any> {
    return Observable.merge(getUser(), getUserProfile())
}

fun getNum1To100(): Observable<Int> {
    return Observable.range(1, 100)
}

fun getNum100To200(): Observable<Int> {
    return Observable.range(100, 200)
}

fun concatOperator(): Observable<Int> {
    return Observable.concat(getNum1To100(), getNum100To200())
}

fun getBlogs(): Observable<List<Blog>> {
    return Observable.just(mBlogList)
}

fun getUsers(): Observable<List<User>> {
    return Observable.just(mUserList)
}

fun zipOperator(): Observable<List<BlogDetail>> {

    return Observable.zip(getUsers(), getBlogs(), BiFunction { t1, t2 -> blogDetail(t1, t2) })
}

fun blogDetail(t1: List<User>, t2: List<Blog>): List<BlogDetail> {
    val listBlogDetail: MutableList<BlogDetail> = emptyList<BlogDetail>().toMutableList()
    t1.forEach { user ->
        t2.forEach { blog ->
            if (blog.userId == user.id) {
                listBlogDetail.add(
                    BlogDetail(blog.id, blog.userId, blog.title, blog.content, user)
                )
            }
        }
    }
    return listBlogDetail
}