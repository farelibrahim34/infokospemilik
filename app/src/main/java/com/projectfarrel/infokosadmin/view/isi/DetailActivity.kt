package com.projectfarrel.infokosadmin.view.isi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.projectfarrel.infokosadmin.R
import com.projectfarrel.infokosadmin.databinding.ActivityDetailBinding
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

        binding.txtKos.setText(nama)
        binding.txtAlamat.setText(alamat)
        binding.btnNomorHp.setText(nohp)
        binding.viewPagerHomeDetail
    }
}