package com.sungdonggu.pagerdict.Java.JobScheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class JavaExampleJobService extends JobService {
    private static final String TAG = "JavaExampleJobService";
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job Started");
        doBackGroundWork(params);
        return false;
    }

    public void doBackGroundWork(JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    if (jobCancelled) {
                        return;
                    }
                    Log.d(TAG, "run : " + i);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "Job finished");
                jobFinished(params, false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;
        return false;
    }
}
