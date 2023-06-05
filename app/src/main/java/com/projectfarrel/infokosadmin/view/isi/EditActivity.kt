package com.projectfarrel.infokosadmin.view.isi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.projectfarrel.infokosadmin.R
import com.projectfarrel.infokosadmin.databinding.ActivityDetailBinding
import com.projectfarrel.infokosadmin.databinding.ActivityEditBinding
import com.projectfarrel.infokosadmin.view.HomeActivity
import com.projectfarrel.infokosadmin.viewmodel.ViewModelDataKos
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class EditActivity : AppCompatActivity() {
    lateinit var binding : ActivityEditBinding
    var id by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
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

        binding.etAddAlamatE.setText(alamat)
        binding.etAddNamaKosE.setText(nama)
        binding.etAddNoHpE.setText(nohp)
        binding.etLinkFotoE.setText(fotokos)
        binding.etLinkFotoSatuE.setText(foto1)
        binding.etLinkFotoDuaE.setText(foto2)
        binding.etLinkFotoTigaE.setText(foto3)
        binding.etLinkMaps.setText(linkMaps)
        binding.etDesc.setText(desc)
        binding.etRate.setText(rate)

        binding.btnEdit.setOnClickListener{
            editData()

            Toast.makeText(this, "Data Berhasil Di Edit", Toast.LENGTH_SHORT).show()

        }

    }

    private fun editData(){
        val namaKos = binding.etAddNamaKosE.text.toString()
        val alamat = binding.etAddAlamatE.text.toString()
        val nomerHp = binding.etAddNoHpE.text.toString()
        val linkFRmh = binding.etLinkFotoE.text.toString()
        val linkSatu = binding.etLinkFotoSatuE.text.toString()
        val linkDua = binding.etLinkFotoDuaE.text.toString()
        val linkTiga = binding.etLinkFotoTigaE.text.toString()
        val linkMaps = binding.etLinkMaps.text.toString()
        val rate = binding.etRate.text.toString()
        val desc = binding.etDesc.text.toString()


        val viewModel = ViewModelProvider(this).get(ViewModelDataKos::class.java)
        viewModel.callEditData(id,alamat,linkDua,linkFRmh,linkSatu,linkTiga,namaKos,nomerHp,linkMaps,rate,desc)
        viewModel.getEditData().observe(this){
            if (it != null){
                Toast.makeText(this, "Data Berhasil Di Edit", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                Toast.makeText(this, "Data Berhasil Di Edit", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        }


    }
}