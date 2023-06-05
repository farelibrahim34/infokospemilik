package com.projectfarrel.infokosadmin.view.isi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.projectfarrel.infokosadmin.R
import com.projectfarrel.infokosadmin.databinding.ActivityEditBinding
import com.projectfarrel.infokosadmin.databinding.ActivityEditPiBinding
import com.projectfarrel.infokosadmin.view.HomeActivity
import com.projectfarrel.infokosadmin.viewmodel.ViewModelDataKos
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class EditPiActivity : AppCompatActivity() {
    lateinit var binding : ActivityEditPiBinding
    var id by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackDetail.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
        }

        val nama = intent.getStringExtra("nama")
        val fotokos = intent.getStringExtra("fotokos")
        val alamat = intent.getStringExtra("alamat")
        val foto1 = intent.getStringExtra("foto1")
        val foto2 = intent.getStringExtra("foto2")
        val foto3 = intent.getStringExtra("foto3")
        val nohp = intent.getStringExtra("nohp")
        val linkMaps = intent.getStringExtra("linkMaps")
        val desc = intent.getStringExtra("desc")
        val rate = intent.getStringExtra("rate")
        id = intent.getStringExtra("idData")!!.toInt()

        binding.etAddAlamatPi.setText(alamat)
        binding.etAddNamaKosPi.setText(nama)
        binding.etAddNoHpPi.setText(nohp)
        binding.etLinkFotoPi.setText(fotokos)
        binding.etLinkFotoSatuPi.setText(foto1)
        binding.etLinkFotoDuaPi.setText(foto2)
        binding.etLinkFotoTigaPi.setText(foto3)
        binding.etLinkMaps.setText(linkMaps)
        binding.etDesc.setText(desc)
        binding.etRate.setText(rate)

        binding.btnEditPi.setOnClickListener{
            editData()

            Toast.makeText(this, "Data Berhasil Di Edit", Toast.LENGTH_SHORT).show()

        }
    }
    private fun editData(){
        val namaKos = binding.etAddNamaKosPi.text.toString()
        val alamat = binding.etAddAlamatPi.text.toString()
        val nomerHp = binding.etAddNoHpPi.text.toString()
        val linkFRmh = binding.etLinkFotoPi.text.toString()
        val linkSatu = binding.etLinkFotoSatuPi.text.toString()
        val linkDua = binding.etLinkFotoDuaPi.text.toString()
        val linkMaps = binding.etLinkMaps.text.toString()
        val linkTiga = binding.etLinkFotoTigaPi.text.toString()
        val rate = binding.etRate.text.toString()
        val desc = binding.etDesc.text.toString()


        val viewModel = ViewModelProvider(this).get(ViewModelDataKos::class.java)
        viewModel.callEditDataPi(id,alamat,linkDua,linkFRmh,linkSatu,linkTiga,namaKos,nomerHp,linkMaps,rate,desc)
        viewModel.getEditDataKosPi().observe(this){
            if (it != null){
                val intent = Intent(this, HomeActivity::class.java)
                Toast.makeText(this, "Data Berhasil Di Edit", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        }


    }
}