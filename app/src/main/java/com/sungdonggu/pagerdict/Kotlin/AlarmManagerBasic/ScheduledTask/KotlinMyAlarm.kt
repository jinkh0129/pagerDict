package com.sungdonggu.pagerdict.Kotlin.AlarmManagerBasic.ScheduledTask

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.provider.Settings
import android.util.Log

class KotlinMyAlarm : BroadcastReceiver() {
    companion object{
        private const val TAG = "ALARM_TEST"
    }
    override fun onReceive(context: Context, intent: Intent) {
        val mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI)
        Log.d(TAG,"onReceive")
        mediaPlayer.start()
    }

}
