package com.example.currencytracker.notifications

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.currencytracker.repository.CurrencyRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class CurrencyWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    val currencyRepository: CurrencyRepository
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val currencyData = currencyRepository.getCurrenciesWithBoundResources()
        val reachedLevelData = currencyRepository.getCurrenciesReachedLevel()
        if(reachedLevelData.isNotEmpty()){
            NotificationHandler.createReminderNotification(context = applicationContext)
        }

        return Result.success()
    }

    companion object {
        const val WORK_NAME = "com.example.currencytracker.notifications.CurrencyWorker"
    }
}

