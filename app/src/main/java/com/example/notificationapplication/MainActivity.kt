package com.example.notificationapplication

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notificationapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "1"

    lateinit var mainBinding: ActivityMainBinding
    var counter = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.sendNotification.setOnClickListener {
            counter += 1
            mainBinding.sendNotification.text = counter.toString()
            if (counter % 5 == 0) {
                startNotification()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun startNotification() {
        val builder = NotificationCompat.Builder(this@MainActivity, CHANNEL_ID)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, "1", NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager : NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            builder.setSmallIcon(R.drawable.baseline_notifications_24).setContentTitle("Title").setContentText("Notification Text")
        }else {
            builder.setSmallIcon(R.drawable.baseline_notifications_24).setContentTitle("Notification Title").setContentText("This is notification text").priority =
                NotificationCompat.PRIORITY_DEFAULT
        }

        val notificationManagerCompat: NotificationManagerCompat = NotificationManagerCompat.from(this@MainActivity)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.POST_NOTIFICATIONS,
                ),
                1
            )
            return
        }
        notificationManagerCompat.notify(1, builder.build())
    }
}