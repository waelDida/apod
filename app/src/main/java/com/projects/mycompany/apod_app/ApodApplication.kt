package com.projects.mycompany.apod_app

import android.app.Application
import android.os.Build
import androidx.work.*
import com.projects.mycompany.apod_app.work.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ApodApplication: Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        applicationScope.launch {
            setupWork()
        }
    }

    private fun setupWork(){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
                    setRequiresDeviceIdle(true)
            }.build()

        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(1,TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(RefreshDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,repeatingRequest)
    }
}