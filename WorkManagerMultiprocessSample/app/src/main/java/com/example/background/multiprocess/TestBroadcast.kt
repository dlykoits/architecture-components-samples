package com.example.background.multiprocess

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.WorkManager
import androidx.work.multiprocess.RemoteWorkManager
import androidx.work.multiprocess.RemoteWorkerService
import com.example.background.multiprocess.MainActivity.Companion.buildOneTimeWorkRemoteWorkRequest

class TestBroadcast: BroadcastReceiver() {

    private val PACKAGE_NAME = "com.example.background.multiprocess"

    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("sansay", "TestBroadcast.onReceive()")
        p0?.let { context ->
            val serviceName = RemoteWorkerService::class.java.name
            val componentName = ComponentName(PACKAGE_NAME, serviceName)
            val oneTimeWorkRequest = buildOneTimeWorkRemoteWorkRequest(
                componentName,
                ExampleRemoteCoroutineWorker::class.java
            )

            WorkManager.getInstance(context).enqueue(oneTimeWorkRequest)
        }
    }
}