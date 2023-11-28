package com.projectfarrel.infokosadmin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projectfarrel.infokosadmin.model.PesanKos
import com.projectfarrel.infokosadmin.model.ResponsePesanKos
import com.projectfarrel.infokosadmin.model.ResponsePesanKosItem
import com.projectfarrel.infokosadmin.network.ApiClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


public class ViewModelPesan: ViewModel() {
    lateinit var postLdPesanKos : MutableLiveData<ResponsePesanKos?>
    lateinit var editLdPesan :MutableLiveData<ResponsePesanKosItem?>
    init {
        postLdPesanKos = MutableLiveData()
        editLdPesan = MutableLiveData()

    }
    fun postPesanKos(): MutableLiveData<ResponsePesanKos?>{
        return postLdPesanKos
    }
    fun editPesanKos(): MutableLiveData<ResponsePesanKosItem?>{
        return editLdPesan
    }
    fun callEditPesan(id : Int,
                      nama: String,
                      namaKos: String,
                      noHp: String,
                      noKamar: String,
                      tglpesan: String,
                      status: String){
        ApiClient.instanceDua.editPesan(id,PesanKos(nama,namaKos,noHp,tglpesan,noKamar,status))
            .enqueue(object :Callback<ResponsePesanKosItem>{
                override fun onResponse(
                    call: Call<ResponsePesanKosItem>,
                    response: Response<ResponsePesanKosItem>
                ) {
                    if(response.isSuccessful){
                        editLdPesan.postValue(response.body())
                    }else{
                        editLdPesan.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponsePesanKosItem>, t: Throwable) {
                    editLdPesan.postValue(null)
                }

            })

    }
    fun callPostPesanKos(nama: String,
                         namaKos: String,
                         noHp: String,
                         noKamar: String,
                         tglpesan: String,
                         status: String){
        ApiClient.instanceDua.addPesan(PesanKos(nama,namaKos,noHp,tglpesan,noKamar,status))
            .enqueue(object : Callback<ResponsePesanKos>{
                override fun onResponse(
                    call: Call<ResponsePesanKos>,
                    response: Response<ResponsePesanKos>
                ) {
                    if(response.isSuccessful){
                        postLdPesanKos.postValue(response.body())
                    }else{
                        postLdPesanKos.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponsePesanKos>, t: Throwable) {
                    postLdPesanKos.postValue(null)
                }

            })
    }
}