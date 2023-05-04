package com.projectfarrel.infokosadmin.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.projectfarrel.infokosadmin.adapter.AdapterDataKos
import com.projectfarrel.infokosadmin.adapter.AdapterDataKosPi
import com.projectfarrel.infokosadmin.adapter.ViewPagerFragmentAdapter
import com.projectfarrel.infokosadmin.databinding.ActivityHomeBinding
import com.projectfarrel.infokosadmin.view.isi.AddActivity
import com.projectfarrel.infokosadmin.view.wrapper.FragmentVpHomeOne
import com.projectfarrel.infokosadmin.view.wrapper.FragmentVpHomeThree
import com.projectfarrel.infokosadmin.view.wrapper.FragmentVpHomeTwo
import com.projectfarrel.infokosadmin.viewmodel.ViewModelDataKos
import dagger.hilt.android.AndroidEntryPoint
import me.relex.circleindicator.CircleIndicator3

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    private lateinit var viewModel : ViewModelDataKos
    private var fragmentList = ArrayList<Fragment>()
    private lateinit var viewPager: ViewPager2
    private lateinit var indicator: CircleIndicator3

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
        dataKos()
        bannerHome()
        dataKosPi()

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
}