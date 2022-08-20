package com.example.rxjavapractice

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.rxjavapractice.databinding.ActivityMainBinding
import io.reactivex.rxjava3.core.Observable

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "Main Activity"
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        /*justOperator()*/

        /*fromOperator()*/

        /*fromIterableOperator()*/

        /*rangeOperator().subscribe({
            Log.d(MainActivity.TAG, "onNext: $it")
        }, {
            Log.d(MainActivity.TAG, "onError: ${it.toString()}")
        }, {
            Log.d(MainActivity.TAG, "onComplete")
        })*/

        /*repeatOperator().subscribe({
            Log.d(MainActivity.TAG, "onNext: $it")
        }, {
            Log.d(MainActivity.TAG, "onError: ${it.toString()}")
        }, {
            Log.d(MainActivity.TAG, "onComplete")
        })*/

        /*intervalOperator().subscribe({
            Log.d(MainActivity.TAG, "onNext: $it")
            callYoung()
        }, {
            Log.d(MainActivity.TAG, "onError: ${it.toString()}")
        }, {
            Log.d(MainActivity.TAG, "onComplete")
        })*/

        /*timerOperator().subscribe({
            Log.d(MainActivity.TAG, "onNext: $it")
            callYoung()
        }, {
            Log.d(MainActivity.TAG, "onError: ${it.toString()}")
        }, {
            Log.d(MainActivity.TAG, "onComplete")
        })*/

        /* createOperator().subscribe({
             Log.d(MainActivity.TAG, "onNext: $it")
         }, {
             Log.d(MainActivity.TAG, "onError: ${it.toString()}")
         }, {
             Log.d(MainActivity.TAG, "onComplete")
         })*/

        /*filterOperator()
            .filter {
                it.age > 10
            }
            .subscribe({
                Log.d(MainActivity.TAG, "onNext: $it")
            }, {
                Log.d(MainActivity.TAG, "onError: ${it.toString()}")
            }, {
                Log.d(MainActivity.TAG, "onComplete")
            })*/

        /*lastOperator()
//            .last(User(1, "demo1", 12))
            .lastElement()
             .subscribe({
                Log.d(MainActivity.TAG, "onNext: $it")
            }, {
                Log.d(MainActivity.TAG, "onError: ${it.toString()}")
            })*/

        /*lastOperatorEmptyList()
//            .last(User(1, "demo1", 12))
//            .lastOrError()
            .lastElement()
            .subscribe({
                Log.d(MainActivity.TAG, "onNext: $it")
            }, {
                Log.d(MainActivity.TAG, "onError: ${it.toString()}")
            })*/

        /*distinctOperator()
            .distinct{
            it.age
        }
            .subscribe({
                Log.d(MainActivity.TAG, "onNext: $it")
            }, {
                Log.d(MainActivity.TAG, "onError: ${it.toString()}")
            }, {
                Log.d(MainActivity.TAG, "onComplete")
            })*/

        /*skipOperator()
//            .skip(2)
//            .distinct{
//                it.age
//            }
//            .skipLast(2)
            .skip(1, TimeUnit.MILLISECONDS)
            .subscribe({
                Log.d(MainActivity.TAG, "onNext: $it")
            }, {
                Log.d(MainActivity.TAG, "onError: ${it.toString()}")
            }, {
                Log.d(MainActivity.TAG, "onComplete")
            })*/

        /*skipOperator()
//            .skip(2)
//            .distinct{
//                it.age
//            }
//            .skipLast(2)
    .skip(1, TimeUnit.MILLISECONDS)
    .subscribe({
        Log.d(MainActivity.TAG, "onNext: $it")
    }, {
        Log.d(MainActivity.TAG, "onError: ${it.toString()}")
    }, {
        Log.d(MainActivity.TAG, "onComplete")
    })*/

        /*bufferOperator()
            .buffer(3)
            .subscribe({
                Log.d(MainActivity.TAG, "onNext: $it")
            }, {
                Log.d(MainActivity.TAG, "onError: ${it.toString()}")
            }, {
                Log.d(MainActivity.TAG, "onComplete")
            })*/

        /*mapOperator()
//            .map { it.age * 2 }
            .map{
                UserProfile(id = it.id, name = it.name, it.age, "https://test.com/${it.id}")
            }
            .subscribe({
                Log.d(MainActivity.TAG, "onNext: $it")
            }, {
                Log.d(MainActivity.TAG, "onError: ${it.toString()}")
            }, {
                Log.d(MainActivity.TAG, "onComplete")
            })*/

        /*flatMapOperator()
            .flatMap {
                getUserProfile(it.id)
            }
            .subscribe({
                Log.d(MainActivity.TAG, "onNext: $it")
            }, {
                Log.d(MainActivity.TAG, "onError: ${it.toString()}")
            }, {
                Log.d(MainActivity.TAG, "onComplete")
            })*/

        /*flatMapOperatorT2()
            .flatMap{
                Observable.fromIterable(it)
            }
            .flatMap{
                getUserProfile(it.id)
            }
//            .map{
//                getUserProfile(it.id)
//            }
            .subscribe({
            Log.d(MainActivity.TAG, "onNext: $it")
        }, {
            Log.d(MainActivity.TAG, "onError: ${it.toString()}")
        }, {
            Log.d(MainActivity.TAG, "onComplete")
        })*/

        groupByOperator()
            .groupBy {
                it.age
            }
            .subscribe({ group ->
                       group.subscribe({
                           Log.d(MainActivity.TAG, "onNext: $it")
                       },{
                           Log.d(MainActivity.TAG, "onError: ${it.toString()}")
                       })
//            Log.d(MainActivity.TAG, "onNext: $it")
        }, {
            Log.d(MainActivity.TAG, "onError: ${it.toString()}")
        }, {
            Log.d(MainActivity.TAG, "onComplete")
        })

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun callYoung() {
        Log.d(TAG, "Young!")
    }


}