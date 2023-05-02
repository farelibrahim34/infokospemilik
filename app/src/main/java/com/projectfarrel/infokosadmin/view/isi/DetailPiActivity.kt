package com.projectfarrel.infokosadmin.view.isi

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.projectfarrel.infokosadmin.R
import com.projectfarrel.infokosadmin.adapter.ImageSliderAdapter
import com.projectfarrel.infokosadmin.databinding.ActivityDetailBinding
import com.projectfarrel.infokosadmin.databinding.ActivityDetailPiBinding
import com.projectfarrel.infokosadmin.model.ImageData
import com.projectfarrel.infokosadmin.view.HomeActivity
import com.projectfarrel.infokosadmin.viewmodel.ViewModelDataKos
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPiActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailPiBinding
    lateinit private var adapterSlide: ImageSliderAdapter
    private val list = ArrayList<ImageData>()
    lateinit var dots : ArrayList<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPiBinding.inflate(layoutInflater)
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
        val kirimId = intent.getStringExtra("id")



        list.add(
            ImageData(foto1.toString())
        )
        list.add(
            ImageData(foto2.toString())
        )
        list.add(
            ImageData(foto3.toString())
        )
        adapterSlide = ImageSliderAdapter(list)
        binding.viewPagerHomeDetail.adapter = adapterSlide
        dots = ArrayList()
        setIndicator()

        binding.viewPagerHomeDetail.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })


        binding.txtKos.text=nama
        binding.txtAlamat.setText(alamat)
        binding.btnNomorHp.setText(nohp)
        binding.viewPagerHomeDetail


        binding.btnNomorHp.setOnClickListener {
            val link = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/ "+nohp))
            startActivity(link)

        }
        binding.btnEditData.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("idData",kirimId)
            intent.putExtra("fotokos",fotokos)
            intent.putExtra("alamat",alamat)
            intent.putExtra("foto1",foto1)
            intent.putExtra("foto2",foto2)
            intent.putExtra("foto3",foto3)
            intent.putExtra("nohp",nohp)
            startActivity(intent)
        }

        binding.btnHapus.setOnClickListener {
            val viewModel = ViewModelProvider(this).get(ViewModelDataKos::class.java)
            viewModel.callDeleteDataPi(id)
            viewModel.getDelDataKosPi().observe(this){
                if (it != null){
                    Toast.makeText(this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
            }

        }



    }
    private fun selectedDot(position: Int) {
        for (i in 0 until list.size){
            if (i == position)
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.warna_button))
            else
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.warna_background_splash))
        }

    }

    private fun setIndicator() {
        for(i in 0 until list.size){
            dots.add(TextView(this))
            dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            dots[i].textSize = 20f
            binding.dots2.addView(dots[i])
        }
    }
}