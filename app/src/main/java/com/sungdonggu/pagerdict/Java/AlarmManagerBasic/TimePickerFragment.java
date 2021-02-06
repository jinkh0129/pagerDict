package com.sungdonggu.pagerdict.Java.AlarmManagerBasic;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.sungdonggu.pagerdict.MainActivity;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private AlarmManager mAlarmManager;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // AlarmManager를 가져온다.
        mAlarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);

        // 현재시간을 지정
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);


        return new TimePickerDialog(getContext(), this, hour, minute, DateFormat.is24HourFormat(getContext()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // TimePicker를 띄우고 선택하면 실행되는 메서드
        // 사용자가 입력한 시간값이 여기로 들어온다.

        // hour와 minute값을 사용자가 입력한 값으로 덮어씌우는 과정
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        // long값으로 언제 알람이 실행되어야하는지를 입력해줘야한다. 3번째 인자
        // 실행되는 것은 4번째 인자로 넣어준다.

        // 알람이 동작할 때 어떤 것이 실행되면 좋을지는 Intent로 지정해준다. ---> 지금은 메인액티비티가 실행되도록
        Intent intent = new Intent(getContext(), MainActivity.class);
        PendingIntent operation = PendingIntent.getActivity(getContext(), 0, intent, 0);
        mAlarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), operation);
    }
}
