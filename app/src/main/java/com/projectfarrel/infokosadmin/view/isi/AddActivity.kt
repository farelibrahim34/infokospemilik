package com.projectfarrel.infokosadmin.view.isi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.projectfarrel.infokosadmin.R
import com.projectfarrel.infokosadmin.databinding.ActivityAddBinding
import com.projectfarrel.infokosadmin.view.HomeActivity
import com.projectfarrel.infokosadmin.viewmodel.ViewModelDataKos
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddData.setOnClickListener {
            val namaKos = binding.etAddNamaKos.text.toString()
            val alamat = binding.etAddAlamat.text.toString()
            val nomerHp = binding.etAddNoHp.text.toString()
            val linkFRmh = binding.etLinkFoto.text.toString()
            val linkSatu = binding.etLinkFotoSatu.text.toString()
            val linkDua = binding.etLinkFotoDua.text.toString()
            val linkTiga = binding.etLinkFotoTiga.text.toString()

            addData(alamat,linkDua,linkFRmh,linkSatu,linkTiga,namaKos,nomerHp)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"add data sukses", Toast.LENGTH_SHORT).show()
        }
        binding.btnAddDataPutri.setOnClickListener {
            val namaKos = binding.etAddNamaKos.text.toString()
            val alamat = binding.etAddAlamat.text.toString()
            val nomerHp = binding.etAddNoHp.text.toString()
            val linkFRmh = binding.etLinkFoto.text.toString()
            val linkSatu = binding.etLinkFotoSatu.text.toString()
            val linkDua = binding.etLinkFotoDua.text.toString()
            val linkTiga = binding.etLinkFotoTiga.text.toString()
            addDataPi(alamat,linkDua,linkFRmh,linkSatu,linkTiga,namaKos,nomerHp)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"add data sukses", Toast.LENGTH_SHORT).show()
        }



    }

    fun addData(alamat: String,
                fotoDua: String,
                fotoKos: String,
                fotoSatu: String,
                fotoTiga: String,
                namaKos: String,
                noHp: String){
        val viewModel = ViewModelProvider(this).get(ViewModelDataKos::class.java)
        viewModel.callPostDataKos(alamat,fotoDua,fotoKos,fotoSatu,fotoTiga,namaKos,noHp)
        viewModel.postDataKos().observe(this,{
            if (it != null){
                Toast.makeText(this,"add data sukses", Toast.LENGTH_SHORT).show()
            }
        })
        finish()

    }

    fun addDataPi(alamat: String,
                  fotoDua: String,
                  fotoKos: String,
                  fotoSatu: String,
                  fotoTiga: String,
                  namaKos: String,
                  noHp: String){
        val viewModel = ViewModelProvider(this).get(ViewModelDataKos::class.java)
        viewModel.callPostDataKosPi(alamat,fotoDua,fotoKos,fotoSatu,fotoTiga,namaKos,noHp)
        viewModel.getPostDataKosPi().observe(this){
            if (it != null){
                Toast.makeText(this,"add data sukses", Toast.LENGTH_SHORT).show()

            }
        }
        finish()

    }
}