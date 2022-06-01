package com.example.lala_coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        CoroutineScope(Dispatchers.Main).launch {
            Log.d("TEST_LOG", "Main Dispatchers is called")
            CoroutineScope(Dispatchers.IO).launch {
                Log.d("TEST_LOG", "IO Dispatchers is called")
            }
        }

        Log.d("TEST_LOG", "Main Thread is called")

        Thread {
            Log.d("TEST_LOG", "thread is called")
            runOnUiThread {
                Log.d("TEST_LOG", "runOnUiThread is called")

            }
        }.start()
    }
}

//_ MainThread, unOnUiThread, Dispatchers.Main은 같은 TID를 가지므로 DisPatchers.Main 컨텍스를 가진 코루틴도 MainThread에서 실행됨을 알 수 있다.