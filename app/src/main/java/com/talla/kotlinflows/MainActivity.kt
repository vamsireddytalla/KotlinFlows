package com.talla.kotlinflows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //this is the producer here we are producing data
        val flow = flow<Int> {
            for (i in 1..20){
                emit(i)
                delay(1000)
            }
        }

        //now we will make consumer here we are consuming data like byte by byte
        GlobalScope.launch {
            flow.buffer().filter {
                // here we will print ony even num using this filter method we will filter result
                it % 2 == 0
            }.map {
                //here we are making even number into multiply see output now
                it * it
            }.collect{
                delay(2000)
                Log.d(TAG, "Server Result : $it")
            }
        }
    }
}