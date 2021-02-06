package com.sungdonggu.pagerdict.Kotlin.JobScheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sungdonggu.pagerdict.R


class KotlinJobSchedulerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_job_scheduler)
    }

    fun kotlinscheduleJob(view: View?) {
        val componentName = ComponentName(this, KotlinExampleJobService::class.java)
        val info = JobInfo.Builder(1234, componentName)
            .setRequiresCharging(true)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            .setPersisted(true)
            .setPeriodic((15 * 60 * 1000).toLong())
            .build()
        val scheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        val resultCode = scheduler.schedule(info)
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job Scheduled")
        } else {
            Log.d(TAG, "Job scheduling failed")
        }
    }

    fun kotlincancelJob(v: View?) {
        val scheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        scheduler.cancel(1234)
        Log.d(TAG, "Job Cancelled")
    }

    companion object {
        private const val TAG = "JobSchedulerActivity"
    }
}