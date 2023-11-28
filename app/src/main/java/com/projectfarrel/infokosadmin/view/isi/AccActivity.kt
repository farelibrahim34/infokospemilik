package com.projectfarrel.infokosadmin.view.isi

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.projectfarrel.infokosadmin.view.HomeActivity
import com.projectfarrel.infokosadmin.viewmodel.ViewModelDataKos
import com.projectfarrel.infokosadmin.viewmodel.ViewModelPesan
import com.projectfarrel.infokoscalo.R
import com.projectfarrel.infokoscalo.databinding.ActivityAccBinding
import com.projectfarrel.infokoscalo.databinding.ActivityAddBinding
import kotlin.properties.Delegates

class AccActivity : AppCompatActivity() {
    lateinit var binding : ActivityAccBinding
    var id by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccBinding.inflate(layoutInflater)
        setContentView(binding.root)
        id = intent.getStringExtra("id")!!.toInt()
        val nama = intent.getStringExtra("nama")
        val namaKos = intent.getStringExtra("namaKos")
        val noHp = intent.getStringExtra("nohp")
        val tgl = intent.getStringExtra("tgl")
        val noKamar = intent.getStringExtra("nokamar")
        val status = intent.getStringExtra("status")
        Log.d("DEBUG_ID",id.toString())
        binding.txtNamaKos.setText(namaKos)
        binding.txtNoHp.setText(noHp)
        binding.txtStatus.setText("Sukses")
        binding.txtOrderBy.setText(nama)
        binding.txtNoKamar.setText(noKamar)
        binding.txtTglOrder.setText(tgl)

        binding.btnAcc.setOnClickListener {
            val nama = binding.txtOrderBy.text.toString()
            val namaKos = binding.txtNamaKos.text.toString()
            val noHp = binding.txtNoHp.text.toString()
            val tgl = binding.txtTglOrder.text.toString()
            val noKamar = binding.txtNoKamar.text.toString()
            val status = binding.txtStatus.text.toString()
            val viewModel = ViewModelProvider(this).get(ViewModelDataKos::class.java)
            viewModel.callUpdatePesan(id,nama,namaKos,noHp,noKamar,tgl,status)
            viewModel.updatePesan().observe(this){
                if (it != null){
                    Toast.makeText(this, "Data Berhasil Di Edit", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
    fun setDetail(){
        val viewModel = ViewModelProvider(this).get(ViewModelDataKos::class.java)
        viewModel.callGetById(id)
        viewModel.getByIdPesan(id).observe(this){
            binding.txtNamaKos.setText(it.namaKos)
            binding.txtNoHp.setText(it.noHp)
            binding.txtStatus.setText(it.status)
            binding.txtOrderBy.setText(it.nama)
            binding.txtNoKamar.setText(it.noKamar)
            binding.txtTglOrder.setText(it.tglpesan)
        }
    }
    fun postPesan(nama: String,
                  namaKos: String,
                  noHp: String,
                  noKamar: String,
                  tglpesan: String,
                  status: String){
        val viewModel = ViewModelProvider(this).get(ViewModelPesan::class.java)
        viewModel.callPostPesanKos(nama,namaKos,noHp,noKamar,tglpesan,status)
        viewModel.postPesanKos().observe(this,{
            if (it != null){
                Toast.makeText(this,"add data sukses", Toast.LENGTH_SHORT).show()
            }
        })
        finish()
    }

}