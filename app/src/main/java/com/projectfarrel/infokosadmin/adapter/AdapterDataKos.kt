package com.projectfarrel.infokosadmin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projectfarrel.infokosadmin.databinding.ItemBinding
import com.projectfarrel.infokosadmin.model.ResponseDataKosItem

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
    }
    override fun getItemCount(): Int {
        return listData.size
    }
}