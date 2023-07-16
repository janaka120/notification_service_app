package com.example.notificationapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat

class ReceiverDismiss: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
         context?.let {
             val notificationManagerCompat: NotificationManagerCompat? = NotificationManagerCompat.from(
                it
            )
             notificationManagerCompat?.cancel(1)
        }
    }
}