package com.projectfarrel.infokosadmin.view.isi

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.projectfarrel.infokosadmin.R
import com.projectfarrel.infokosadmin.databinding.ActivityDetailBinding
import com.projectfarrel.infokosadmin.view.HomeActivity
import com.projectfarrel.infokosadmin.viewmodel.ViewModelDataKos
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDetail()

    }
    private fun setDetail(){
        val nama = intent.getStringExtra("nama")
        val fotokos = intent.getStringExtra("fotokos")
        val alamat = intent.getStringExtra("alamat")
        val foto1 = intent.getStringExtra("foto1")
        val foto2 = intent.getStringExtra("foto2")
        val foto3 = intent.getStringExtra("foto3")
        val nohp = intent.getStringExtra("nohp")
        val id = intent.getStringExtra("id")!!.toInt()

        binding.txtKos.setText(nama)
        binding.txtAlamat.setText(alamat)
        binding.btnNomorHp.setText(nohp)
        binding.viewPagerHomeDetail

        binding.btnNomorHp.setOnClickListener {
            val link = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/ "+nohp))
            startActivity(link)

        }

        binding.btnHapus.setOnClickListener {
            val viewModel = ViewModelProvider(this).get(ViewModelDataKos::class.java)
            viewModel.callDeleteData(id)
            viewModel.getDelDataKos().observe(this){
                if (it != null){
                    Toast.makeText(this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
            }

        }



    }
}