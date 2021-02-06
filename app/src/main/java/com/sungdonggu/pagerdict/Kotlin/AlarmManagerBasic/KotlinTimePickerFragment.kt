package com.sungdonggu.pagerdict.Kotlin.AlarmManagerBasic

import android.app.AlarmManager
import android.app.Dialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.sungdonggu.pagerdict.MainActivity
import java.util.*


class KotlinTimePickerFragment : DialogFragment(),
    OnTimeSetListener {
    private var mAlarmManager: AlarmManager? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // AlarmManager를 가져온다.
        mAlarmManager = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // 현재시간을 지정
        val c = Calendar.getInstance()
        val hour = c[Calendar.HOUR_OF_DAY]
        val minute = c[Calendar.MINUTE]
        return TimePickerDialog(
            context, this, hour, minute, DateFormat.is24HourFormat(
                context
            )
        )
    }

    // onTimeSet메서드는 DialogFragment를 상속했을 때 구현해야하는 메서드
    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        // TimePicker를 띄우고 선택하면 실행되는 메서드
        // 사용자가 입력한 시간값이 여기로 들어온다.

        // hour와 minute값을 사용자가 입력한 값으로 덮어씌우는 과정
        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = hourOfDay
        calendar[Calendar.MINUTE] = minute

        // long값으로 언제 알람이 실행되어야하는지를 입력해줘야한다. 3번째 인자
        // 실행되는 것은 4번째 인자로 넣어준다.

        // 알람이 동작할 때 어떤 것이 실행되면 좋을지는 Intent로 지정해준다. ---> 지금은 메인액티비티가 실행되도록
        val intent = Intent(context, KotlinAlarmActivity::class.java)
        val operation = PendingIntent.getActivity(context, 0, intent, 0)
        mAlarmManager!![AlarmManager.RTC_WAKEUP, calendar.timeInMillis] = operation
    }
}
