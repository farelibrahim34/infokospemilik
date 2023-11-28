package com.projectfarrel.infokosadmin.adapter

import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.projectfarrel.infokosadmin.model.ResponsePesanKosItem
import com.projectfarrel.infokosadmin.view.isi.AccActivity
import com.projectfarrel.infokosadmin.view.isi.DetailActivity
import com.projectfarrel.infokoscalo.databinding.ItemPesanBinding

class AdapterNotif(val listNotifikasi: List<ResponsePesanKosItem>):RecyclerView.Adapter<AdapterNotif.ViewHolder>(){

    class ViewHolder(var binding: ItemPesanBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemPesanBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNotifikasi.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtNamaKos.text = listNotifikasi[position].namaKos
        holder.binding.txtOrderBy.text = listNotifikasi[position].nama
        holder.binding.txtNoHp.text = "Nomor Hp : "+listNotifikasi[position].noHp
        holder.binding.txtTglOrder.text = listNotifikasi[position].tglpesan
        holder.binding.txtNoKamar.text = "Nomor Kamar : "+listNotifikasi[position].noKamar
        holder.binding.txtStatus.text = listNotifikasi[position].status
        val teks = listNotifikasi[position].status
        teks+""

        holder.binding.cardView2.setOnClickListener{
            val detail = Intent(it.context, AccActivity::class.java)
            detail.putExtra("nama",listNotifikasi[position].nama)
            detail.putExtra("namaKos",listNotifikasi[position].namaKos)
            detail.putExtra("nohp",listNotifikasi[position].noHp)
            detail.putExtra("nokamar",listNotifikasi[position].noKamar)
            detail.putExtra("tgl",listNotifikasi[position].tglpesan)
            detail.putExtra("status",listNotifikasi[position].status)
            detail.putExtra("id",listNotifikasi[position].id)
            it.context.startActivity(detail)
//            AlertDialog.Builder(it.context)
//                .setTitle("Terima")
//                .setPositiveButton("Ya"){ dialogInterface: DialogInterface, i: Int ->
//                    holder.binding.txtStatus.setText("Sukses")
//
//                }
//                .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
//                }
//                .show()

        }
    }

}