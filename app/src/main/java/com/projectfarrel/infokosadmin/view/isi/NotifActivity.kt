package com.projectfarrel.infokosadmin.view.isi

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.workDataOf
import com.projectfarrel.infokosadmin.adapter.AdapterNotif
import com.projectfarrel.infokosadmin.view.HomeActivity
import com.projectfarrel.infokosadmin.viewmodel.ViewModelDataKos
import com.projectfarrel.infokoscalo.R
import com.projectfarrel.infokoscalo.databinding.ActivityEditPiBinding
import com.projectfarrel.infokoscalo.databinding.ActivityNotifBinding
import java.util.concurrent.TimeUnit

class NotifActivity : AppCompatActivity() {
    lateinit var binding : ActivityNotifBinding
    private val CHANNEL_ID = "chanel_id_example_01"
    private val notificationId = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notif)
        binding = ActivityNotifBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getNotif()
        createNotification()

        binding.ivBackDetail.setOnClickListener{
            startActivity(Intent(this,HomeActivity::class.java))
        }

    }


    fun getNotif(){
        val viewModel = ViewModelProvider(this).get(ViewModelDataKos::class.java)
        viewModel.getPesanKos().observe(this){
            if (it != null){
                binding.rvPesan.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                val adapter = AdapterNotif(it)
                binding.rvPesan.adapter = adapter

            }
        }
        viewModel.callApiPesanKos()
    }

    private fun createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,"First Channel",
                NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "test description for my channel"

            val notificationManagaer = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManagaer.createNotificationChannel(channel)
        }
    }
    fun notif(){
        var builder = NotificationCompat.Builder(this,CHANNEL_ID)
        builder.setSmallIcon(R.drawable.logokost)
            .setContentTitle("test title")
            .setContentText("this is a test description")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            notify(1,builder.build())
        }
    }



}