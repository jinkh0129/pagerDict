package com.sungdonggu.pagerdict.Kotlin.AlarmManagerBasic.Practice

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.sungdonggu.pagerdict.R

class Receiver : BroadcastReceiver() {
    companion object {
        private const val TAG = "RECEIVER"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        /**반복작업을 할 때 어떤 작업을 할지 여기다가 적어준다*/
        /**INTENT로 넘겨줬으니까 받고 다시 넘겨줄 때도 INTENT를 쓰나?*/
        Log.d(TAG, "onReceive")
        for (i in 0..5) {
            Log.d(TAG, "run : ${i}")
        }
        val got = intent!!.getStringExtra("value")
        Log.d(TAG, "from intent : ${got}")


    }
}