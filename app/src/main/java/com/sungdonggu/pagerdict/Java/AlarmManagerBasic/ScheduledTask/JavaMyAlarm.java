package com.sungdonggu.pagerdict.Java.AlarmManagerBasic.ScheduledTask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;

public class JavaMyAlarm extends BroadcastReceiver {
    private static final String TAG = "JAVATEST";

    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        Log.d(TAG, "JavaMyAlarm");
        mediaPlayer.start();
    }
}
