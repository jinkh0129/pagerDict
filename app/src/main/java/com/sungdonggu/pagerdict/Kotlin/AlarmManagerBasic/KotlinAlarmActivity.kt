package com.sungdonggu.pagerdict.Kotlin.AlarmManagerBasic

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sungdonggu.pagerdict.Java.AlarmManagerBasic.TimePickerFragment
import com.sungdonggu.pagerdict.R

class KotlinAlarmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_alarm)
    }

    fun showAlarmDialog(view: View?) {
        val kotlinTimePickerFragment = KotlinTimePickerFragment()
        kotlinTimePickerFragment.show(supportFragmentManager, "timePicker")
    }
}