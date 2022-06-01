package com.example.lala_coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity3 : AppCompatActivity() {
    private var value: Deferred<Int>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        value = CoroutineScope(Dispatchers.Main).async {
            30
        }
        CoroutineScope(Dispatchers.Main).launch {
            val result = awaitTest()
        }
    }

    private suspend fun awaitTest(): Int? { //_ suspend fun은 코루틴 블록에서만 실행 가능
        val t = value?.await()
        return t
    }
}

