package com.sungdonggu.pagerdict.Kotlin.AlarmManagerBasic.ScheduledTask

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sungdonggu.pagerdict.R
import java.util.*


class KotlinScheduledActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "ALARM_TEST"
    }

    var timePicker: TimePicker? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_scheduled)

        timePicker = findViewById<View>(R.id.kotlin_timepicker) as TimePicker

        findViewById<View>(R.id.kotlin_buttomSetAlarm).setOnClickListener {
            val calendar = Calendar.getInstance()

            //  안드로이드 버전분기
            if (Build.VERSION.SDK_INT >= 23) {
                calendar[calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH], timePicker!!.hour, timePicker!!.minute] =
                    0
                Log.d(TAG, "in calendar : ${timePicker!!.hour} / ${timePicker!!.minute}")
            } else {
                calendar[calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH], timePicker!!.currentHour, timePicker!!.currentMinute] =
                    0
            }
            
            // 버튼을 클릭까지해야하네
            Log.d(TAG, "timeInMillis : ${calendar.timeInMillis}")
            Log.d(TAG, "time : ${calendar.time}")

            setAlarm(calendar.timeInMillis)
        }
    }

    private fun setAlarm(timeInMillis: Long) {
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, KotlinMyAlarm::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.setRepeating(
            AlarmManager.RTC,
            timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show()
    }
}