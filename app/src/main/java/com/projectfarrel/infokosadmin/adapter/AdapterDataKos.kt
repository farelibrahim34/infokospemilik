package com.projectfarrel.infokosadmin.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projectfarrel.infokosadmin.databinding.ItemBinding
import com.projectfarrel.infokosadmin.model.ResponseDataKosItem
import com.projectfarrel.infokosadmin.view.isi.DetailActivity

class AdapterDataKos(private var listData:List<ResponseDataKosItem>): RecyclerView.Adapter<AdapterDataKos.ViewHolder>() {
    class ViewHolder(var binding : ItemBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtNamaKos.text = listData[position].namaKos
        Glide.with(holder.itemView).load(listData[position].fotoKos).fitCenter().into(holder.binding.ivImage)
        holder.binding.txtRate.text = listData[position].rate

        holder.binding.cardList.setOnClickListener {
            val detail = Intent(it.context, DetailActivity::class.java)
            detail.putExtra("id",listData[position].id)
            detail.putExtra("fotokos",listData[position].fotoKos)
            detail.putExtra("nama",listData[position].namaKos)
            detail.putExtra("alamat",listData[position].alamat)
            detail.putExtra("foto1",listData[position].fotoSatu)
            detail.putExtra("foto2",listData[position].fotoDua)
            detail.putExtra("foto3",listData[position].fotoTiga)
            detail.putExtra("nohp",listData[position].noHp)
            detail.putExtra("linkMaps",listData[position].linkMaps)
            detail.putExtra("desc",listData[position].desc)
            detail.putExtra("rate",listData[position].rate)
            it.context.startActivity(detail)
        }

    }
    override fun getItemCount(): Int {
        return listData.size
    }
}