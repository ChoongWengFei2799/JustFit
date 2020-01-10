package com.example.justfit.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.justfit.R
import com.example.justfit.databinding.FragmentAlarmBinding
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import com.example.justfit.NotificationPublisher
import android.content.Context.ALARM_SERVICE
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.android.synthetic.main.fragment_alarm.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class AlarmFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        val binding: FragmentAlarmBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_alarm,
            container,
            false
        )

        binding.button7.setOnClickListener {

                val timeSetListener = editText3

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val name = "JustFit"
                    val descriptionText = ":)"
                    val importance = NotificationManager.IMPORTANCE_DEFAULT
                    val channel = NotificationChannel("0", name, importance).apply {
                        description = descriptionText
                    }
                    // Register the channel with the system
                    val notificationManager: NotificationManager =
                        activity!!.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.createNotificationChannel(channel)
                }

                val intent = Intent(activity, NotificationPublisher::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }

                val pendingIntent: PendingIntent = PendingIntent.getActivity(activity, 0, intent, 0)
                var builder = NotificationCompat.Builder(context!!, "0")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Reminder for Workout at: ")
                    .setContentText("${timeSetListener.hour}:${timeSetListener.minute}")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)

                with(NotificationManagerCompat.from(context!!)) {
                    // notificationId is a unique int for each notification that you must define
                    notify(0, builder.build())
                }

                Toast.makeText(activity, "Notification Created", Toast.LENGTH_LONG).show()
            }


        return binding.root
    }
}
