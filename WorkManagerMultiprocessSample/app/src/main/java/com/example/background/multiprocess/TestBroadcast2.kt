package com.example.background.multiprocess

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.WorkManager
import com.example.background.multiprocess.MainActivity.Companion.buildOneTimeWorkRemoteWorkRequest

class TestBroadcast2 : BroadcastReceiver() {

    private val PACKAGE_NAME = "com.example.background.multiprocess"

    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("sansay", "TestBroadcast2.onReceive()")
        p0?.let { context ->
            val serviceName = RemoteWorkerService2::class.java.name
            val componentName = ComponentName(PACKAGE_NAME, serviceName)

            val oneTimeWorkRequest = buildOneTimeWorkRemoteWorkRequest(
                componentName,
                ExampleRemoteListenableWorker::class.java
            )
            WorkManager.getInstance(context).enqueue(oneTimeWorkRequest)
        }
    }
}