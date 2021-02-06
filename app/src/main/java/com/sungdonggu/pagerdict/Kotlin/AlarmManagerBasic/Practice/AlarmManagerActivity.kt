package com.sungdonggu.pagerdict.Kotlin.AlarmManagerBasic.Practice

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.Time
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.sungdonggu.pagerdict.R
import java.util.*

class AlarmManagerActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "RECEIVER"
    }

    private lateinit var tv_test: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_manager)

    }

    fun updatePrice(view: View?) {
        tv_test = findViewById(R.id.tv_test)
        tv_test.setText("예약하는 중")

        /**
         * 우선 기본적으로 FIREBASE의 정보를 가져온다.
         * --> 어플을 처음 설치할 때가 16시 이전이면 가져온 데이터가 없기 때문에
         * 그리고 현재시간과 예약시간(16시)을 비교해서
         * 16시가 넘었으면 업데이트를 하고,
         * 넘지않았으면 16시에 실시하도록 만든다.
         */
//        val currentTime = Calendar.getInstance()
//        val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
//        if (currentHour > 16) {
//            /**예약날짜를 다음날 16시로 잡아야지*/
//            val bookedTime = Calendar.getInstance()
//            bookedTime.add(Calendar.DAY_OF_MONTH, +1)
//            bookedTime.set(Calendar.HOUR_OF_DAY, 16)
//            bookedTime.set(Calendar.MINUTE, 0)
//            bookedTime.set(Calendar.SECOND, 0)
//            // 예약시간은 다음날 16시 00분 00초
//            Log.d(TAG, "bookedTime(after 16) : ${bookedTime.time}")
//            setJob(bookedTime.timeInMillis)
//        } else {
//            /**예약날짜를 오늘 16시로 맞춰야지**/
//            val bookedTime = Calendar.getInstance()
//            bookedTime.set(Calendar.HOUR_OF_DAY, 16)
//            bookedTime.set(Calendar.MINUTE, 0)
//            bookedTime.set(Calendar.SECOND, 0)
//            // 예약시간은 오늘 16시 00분 00초
//            Log.d(TAG, "bookedTime(before 16) : ${bookedTime.time}")
//            setJob(bookedTime.timeInMillis)
//        }

        val testTime = Calendar.getInstance()
//        testTime.set(Calendar.HOUR_OF_DAY, 21)
        testTime.set(Calendar.MINUTE, 20)
        testTime.set(Calendar.SECOND, 0)
        Log.d(TAG, "time : ${testTime.time}")
        setJob(testTime.timeInMillis)
    }

    private fun setJob(bookedTime: Long) {
        Thread(Runnable {
            // (1) AlarmManager를 하기 위해서는 변수를 하나 생성
            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            // (2) 반복으로 할 작업을 적어놓은 Receiver클래스를 인텐트에 넣는다.
            val intent = Intent(this, Receiver::class.java)
            intent.putExtra("value", "worked!")
            // (3) PendingIntent를 만든다
            val pendingIntent = PendingIntent.getBroadcast(this, 200, intent, 0)
            alarmManager.setInexactRepeating(
                AlarmManager.RTC, // 실제 시간을 기준으로
                bookedTime, // 언제 수행할 것인지
                AlarmManager.INTERVAL_FIFTEEN_MINUTES, // 반복간격은 어떻게 되는지
                pendingIntent // 어떤 작업을 할 것인지
            )
            tv_test.setText("예약 완료")
        }).start()
        Toast.makeText(this, "START", Toast.LENGTH_SHORT).show()

    }

}