package com.projectfarrel.infokosadmin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projectfarrel.infokosadmin.model.DataKos
import com.projectfarrel.infokosadmin.model.ResponseDataKos
import com.projectfarrel.infokosadmin.model.ResponseDataKosItem
import com.projectfarrel.infokosadmin.network.ApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelDataKos@Inject constructor(private val api : ApiInterface): ViewModel() {
    lateinit var ldDataKos : MutableLiveData<List<ResponseDataKosItem>>
    lateinit var postLdDataKos : MutableLiveData<ResponseDataKos?>
    lateinit var deleteDataKos : MutableLiveData<ResponseDataKosItem?>

    init {
        ldDataKos = MutableLiveData()
        postLdDataKos = MutableLiveData()
        deleteDataKos = MutableLiveData()
    }

    fun getDataKos():MutableLiveData<List<ResponseDataKosItem>>{
        return ldDataKos
    }
    fun postDataKos():MutableLiveData<ResponseDataKos?>{
        return postLdDataKos
    }
    fun getDelDataKos(): MutableLiveData<ResponseDataKosItem?>{
        return deleteDataKos
    }

    fun callApiDataKos(){
        api.getAllDataKos()
            .enqueue(object :Callback<List<ResponseDataKosItem>>{
                override fun onResponse(
                    call: Call<List<ResponseDataKosItem>>,
                    response: Response<List<ResponseDataKosItem>>
                ) {
                    if (response.isSuccessful){
                        ldDataKos.postValue(response.body())
                    }else{
                        ldDataKos.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataKosItem>>, t: Throwable) {
                    ldDataKos.postValue(null)
                }

            })
    }

    fun callPostDataKos(alamat: String,
                        fotoDua: String,
                        fotoKos: String,
                        fotoSatu: String,
                        fotoTiga: String,
                        namaKos: String,
                        noHp: String){
        api.addDataKos(DataKos(alamat,fotoDua,fotoKos,fotoSatu,fotoTiga,namaKos,noHp))
            .enqueue(object : Callback<ResponseDataKos>{
                override fun onResponse(
                    call: Call<ResponseDataKos>,
                    response: Response<ResponseDataKos>
                ) {
                    if (response.isSuccessful){
                        postLdDataKos.postValue(response.body())
                    }else{
                        postLdDataKos.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataKos>, t: Throwable) {
                    postLdDataKos.postValue(null)
                }

            })
    }

    fun callDeleteData(id : Int){
        api.deleteDataKos(id)
            .enqueue(object : Callback<ResponseDataKosItem>{
                override fun onResponse(
                    call: Call<ResponseDataKosItem>,
                    response: Response<ResponseDataKosItem>
                ) {
                    if (response.isSuccessful){
                        deleteDataKos.postValue(response.body())
                    }else{
                        deleteDataKos.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataKosItem>, t: Throwable) {
                    deleteDataKos.postValue(null)
                }

            })
    }


}