package com.example.lala_coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = ArrayList<Int>()
        CoroutineScope(Dispatchers.Main).launch {
            val launchJob = CoroutineScope(Dispatchers.Main).launch { //_ 결과 값 리턴하지 않고 해당 코루틴을 제어할 수 있는 job을 리턴
                for (i in 0 until 10) {
                    list.add(i)
                }
            }

            val asyncJob = CoroutineScope(Dispatchers.Default).async { //_ async 블록의 마지막 값이 Diferred로 Wrapping 되며 Differred를 리턴
                for (i in 10 until 20) {
                    list.add(i)
                }
                val value = list
                value //_ == Deferred<ArrayList<Int>>
            }

            launchJob.join() //_ 코루틴이 완료될 때 까지 대기
            Log.d("TEST_LOG", "${list}") //_ 코루틴 완료 된 후 list 출력 / launchJob을 join()을 통해 결과 값을 얻을 때 까지 대기하지 않으면 list는 empty 상태로 로그에 출력
            val resultList = asyncJob.await() //_ 코루틴이 완료될 때 까지 대기 / 별도의 delay가 없더라도 Dispatchers.Default 컨텍스트로 얻어진 스레드 풀로부터 결과 값을 수신할 떄 까지 Main Thread가 일시 중지 된다.

            Log.d("TEST_LOG", "${resultList}") //_ Diferred에서 await() 메소드를 통해 결과값을 수신하여 출력
            Log.d("TEST_LOG", "${list}") //_ 코루틴 완료 된 후 list 출력 / asyncJob을 await()을 통해 결과 값을 얻을 때 까지 대기하지 않으면 list의 결과값은 업데이트 되지 않은채 로그에 출력됨.
        }

    }
}