package com.sungdonggu.pagerdict.Kotlin.JobScheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log


class KotlinExampleJobService : JobService() {
    companion object {
        private const val TAG = "ExampleJobService"
    }

    private var jobCancelled = false

    override fun onStartJob(params: JobParameters): Boolean {
        Log.d(TAG, "Job Started : ${params.jobId}")
        doBackGroundWork(params)
        return false
    }

    fun doBackGroundWork(params: JobParameters?) {
        Thread(Runnable {
            for (i in 0..9) {
                if (jobCancelled) {
                    return@Runnable
                }
                Log.d(TAG, "run : $i")
                try {
                    Thread.sleep(500)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            Log.d(TAG, "Job finished")
            jobFinished(params, false)
        }).start()
    }

    override fun onStopJob(params: JobParameters): Boolean {
        Log.d(TAG, "Job cancelled before completion")
        jobCancelled = true
        return false
    }


}
