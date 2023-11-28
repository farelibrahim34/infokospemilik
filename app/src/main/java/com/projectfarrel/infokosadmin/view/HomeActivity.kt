package com.projectfarrel.infokosadmin.view

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.projectfarrel.infokosadmin.adapter.AdapterDataKos
import com.projectfarrel.infokosadmin.adapter.AdapterDataKosPi
import com.projectfarrel.infokosadmin.adapter.AdapterNotif
import com.projectfarrel.infokosadmin.adapter.ViewPagerFragmentAdapter
import com.projectfarrel.infokosadmin.auth.LoginActivity
import com.projectfarrel.infokoscalo.databinding.ActivityHomeBinding
import com.projectfarrel.infokosadmin.datastore.DataStoreLogin
import com.projectfarrel.infokosadmin.view.isi.AddActivity
import com.projectfarrel.infokosadmin.view.isi.NotifActivity
import com.projectfarrel.infokosadmin.view.wrapper.FragmentVpHomeOne
import com.projectfarrel.infokosadmin.view.wrapper.FragmentVpHomeThree
import com.projectfarrel.infokosadmin.view.wrapper.FragmentVpHomeTwo
import com.projectfarrel.infokosadmin.viewmodel.ViewModelDataKos
import com.projectfarrel.infokoscalo.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.relex.circleindicator.CircleIndicator3
import kotlin.properties.Delegates


class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    private lateinit var viewModel : ViewModelDataKos
    private var fragmentList = ArrayList<Fragment>()
    private lateinit var viewPager: ViewPager2
    private lateinit var dataLogin : DataStoreLogin
    private lateinit var indicator: CircleIndicator3
    private val CHANNEL_ID = "chanel_id_example_01"
    var idNotif by Delegates.notNull<Int>()


    private lateinit var  handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)

//            AlertDialog.Builder(this)
//                .setTitle("Tambah Data")
//                .setMessage("Silahkan Pilih Tambah Kos Putra/Putri")
//                .setPositiveButton("Putra"){ dialogInterface: DialogInterface, i: Int ->
//                    val intent = Intent(this, AddActivity::class.java)
//                    startActivity(intent)
//                }
//                .setNegativeButton("Putri"){ dialogInterface: DialogInterface, i: Int ->
//                    val intent = Intent(this, AddPiActivity::class.java)
//                    startActivity(intent)
//                }
//                .show()
        }
        dataLogin = DataStoreLogin(this)
        binding.btnLogout.setOnClickListener{
            startActivity(Intent(this@HomeActivity, NotifActivity::class.java))
            finish()
        }
//        binding.btnLogout.setOnClickListener {
//            GlobalScope.launch {
//                startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
//                finish()
//            }
//        }
        dataKos()
        dataKosPi()
        bannerHome()
//        getAllData()

        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable{
            var index = 0
            override fun run() {
                if (index == fragmentList.size)
                    index = 0
                Log.e("Runnable, ","$index")
                binding.viewPagerHome.setCurrentItem(index)
                index++
                handler.postDelayed(this,3000)
            }

        }
        handler.post(runnable)

        createNotificationChanncel()
        notif()

    }
    private fun createNotificationChanncel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,"First Channel",
                NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "test description for my channel"

            val notificationManagaer = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManagaer.createNotificationChannel(channel)
        }
    }
    fun notif(){
        val intent = Intent(this@HomeActivity, NotifActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_IMMUTABLE)
        var builder = NotificationCompat.Builder(this,CHANNEL_ID)
        builder.setSmallIcon(R.drawable.logokost)
            .setContentTitle("ADA PESANAN")
            .setContentText("Cek Pesanan Di Halaman Notifikasi")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)

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
    fun dataKos(){
        viewModel = ViewModelProvider(this).get(ViewModelDataKos::class.java)
        viewModel.getDataKos().observe(this){
            if (it != null){
                binding.rvPutra.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
                val adapter = AdapterDataKos(it)
                binding.rvPutra.adapter = adapter

            }
        }
        viewModel.callApiDataKos()
    }
    fun dataKosPi(){
        viewModel.getDataKosPi().observe(this){
            if (it != null){
                binding.progressBar.visibility = View.GONE
                binding.rvPutri.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
                val adapter = AdapterDataKosPi(it)
                binding.rvPutri.adapter = adapter
            }
        }
        viewModel.callApiDataKosPi()
    }
    fun bannerHome(){
        castView()

        fragmentList.add(FragmentVpHomeOne())
        fragmentList.add(FragmentVpHomeTwo())
        fragmentList.add(FragmentVpHomeThree())

        viewPager.adapter = ViewPagerFragmentAdapter(this,fragmentList)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        indicator.setViewPager(viewPager)

    }
    private fun castView() {
        viewPager = binding.viewPagerHome
        indicator = binding.indicatorBanner
    }
    override fun onBackPressed() {

        AlertDialog.Builder(this)
            .setTitle("Tutup Aplikasi")
            .setMessage("Yakin tutup dari aplikasi?")
            .setPositiveButton("Ya"){ dialogInterface: DialogInterface, i: Int ->
                finishAffinity()
            }
            .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }
            .show()
    }

    override fun onResume() {
        super.onResume()
        dataKos()
        dataKosPi()
    }

    override fun onStart() {
        super.onStart()
        dataKos()
        dataKosPi()
    }

    override fun onPause() {
        super.onPause()
        dataKos()
        dataKosPi()
    }

    override fun onStop() {
        super.onStop()
        dataKos()
        dataKosPi()
    }

    override fun onRestart() {
        super.onRestart()
        dataKos()
        dataKosPi()
    }
//    private fun getAllData(){
//        val swipeRefresh = binding.swipeRefresh
//        swipeRefresh.setOnRefreshListener {
//            if(swipeRefresh.isRefreshing){
//                dataKos()
//                dataKosPi()
//                swipeRefresh.isRefreshing = false
//            }
//        }
//
//    }


}