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
    lateinit var editLdDataKos : MutableLiveData<ResponseDataKosItem?>

    lateinit var ldDataKosPi : MutableLiveData<List<ResponseDataKosItem>>
    lateinit var postLdDataKosPi :MutableLiveData<ResponseDataKos?>
    lateinit var deleteDataKosPi : MutableLiveData<ResponseDataKosItem?>
    lateinit var editLdDataKosPi :MutableLiveData<ResponseDataKosItem?>

    init {
        ldDataKos = MutableLiveData()
        postLdDataKos = MutableLiveData()
        deleteDataKos = MutableLiveData()
        editLdDataKos = MutableLiveData()

        ldDataKosPi = MutableLiveData()
        postLdDataKosPi = MutableLiveData()
        deleteDataKosPi = MutableLiveData()
        editLdDataKosPi = MutableLiveData()
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
    fun getEditData():MutableLiveData<ResponseDataKosItem?>{
        return editLdDataKos
    }


    fun getDataKosPi(): MutableLiveData<List<ResponseDataKosItem>>{
        return ldDataKosPi
    }
    fun getPostDataKosPi(): MutableLiveData<ResponseDataKos?> {
        return postLdDataKosPi
    }
    fun getDelDataKosPi():MutableLiveData<ResponseDataKosItem?>{
        return deleteDataKosPi
    }
    fun getEditDataKosPi(): MutableLiveData<ResponseDataKosItem?>{
        return editLdDataKosPi
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
    fun callApiDataKosPi(){
        api.getAllDataKosPutri()
            .enqueue(object  : Callback<List<ResponseDataKosItem>>{
                override fun onResponse(
                    call: Call<List<ResponseDataKosItem>>,
                    response: Response<List<ResponseDataKosItem>>
                ) {
                    if (response.isSuccessful){
                        ldDataKosPi.postValue(response.body())
                    }else{
                        ldDataKosPi.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataKosItem>>, t: Throwable) {
                    ldDataKosPi.postValue(null)
                }

            })
    }

    fun callPostDataKos(alamat: String,
                        fotoDua: String,
                        fotoKos: String,
                        fotoSatu: String,
                        fotoTiga: String,
                        namaKos: String,
                        noHp: String,
                        linkMaps: String){
        api.addDataKos(DataKos(alamat,fotoDua,fotoKos,fotoSatu,fotoTiga,namaKos,noHp,linkMaps))
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
    fun callPostDataKosPi(alamat: String,
                          fotoDua: String,
                          fotoKos: String,
                          fotoSatu: String,
                          fotoTiga: String,
                          namaKos: String,
                          noHp: String,
                          linkMaps: String){
        api.addDataKosPutri(DataKos(alamat,fotoDua,fotoKos,fotoSatu,fotoTiga,namaKos,noHp,linkMaps))
            .enqueue(object : Callback<ResponseDataKos>{
                override fun onResponse(
                    call: Call<ResponseDataKos>,
                    response: Response<ResponseDataKos>
                ) {
                    if (response.isSuccessful){
                        postLdDataKosPi.postValue(response.body())
                    }else{
                        postLdDataKosPi.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataKos>, t: Throwable) {
                    postLdDataKosPi.postValue(null)
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
    fun callDeleteDataPi(id :Int){
        api.deleteDataKosPutri(id)
            .enqueue(object : Callback<ResponseDataKosItem>{
                override fun onResponse(
                    call: Call<ResponseDataKosItem>,
                    response: Response<ResponseDataKosItem>
                ) {
                    if (response.isSuccessful){
                        deleteDataKosPi.postValue(response.body())
                    }else{
                        deleteDataKosPi.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataKosItem>, t: Throwable) {
                    deleteDataKosPi.postValue(null)
                }

            })
    }

    fun callEditData(id: Int,
                     alamat: String,
                     fotoDua: String,
                     fotoKos: String,
                     fotoSatu: String,
                     fotoTiga: String,
                     namaKos: String,
                     noHp: String,
                     linkMaps: String){
        api.editDataKos(id,DataKos(alamat,fotoDua,fotoKos,fotoSatu,fotoTiga,namaKos,noHp,linkMaps))
            .enqueue(object : Callback<ResponseDataKosItem>{
                override fun onResponse(
                    call: Call<ResponseDataKosItem>,
                    response: Response<ResponseDataKosItem>
                ) {
                    if (response.isSuccessful){
                        editLdDataKos.postValue(response.body())
                    }else{
                        editLdDataKos.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataKosItem>, t: Throwable) {
                    editLdDataKos.postValue(null)
                }

            })
    }
    fun callEditDataPi(id: Int,
                       alamat: String,
                       fotoDua: String,
                       fotoKos: String,
                       fotoSatu: String,
                       fotoTiga: String,
                       namaKos: String,
                       noHp: String,
                       linkMaps: String){
        api.editDataKosPutri(id,DataKos(alamat,fotoDua,fotoKos,fotoSatu,fotoTiga,namaKos,noHp,linkMaps))
            .enqueue(object : Callback<ResponseDataKosItem>{
                override fun onResponse(
                    call: Call<ResponseDataKosItem>,
                    response: Response<ResponseDataKosItem>
                ) {
                    if (response.isSuccessful){
                        editLdDataKosPi.postValue(response.body())
                    }else{
                        editLdDataKosPi.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataKosItem>, t: Throwable) {
                    editLdDataKosPi.postValue(null)
                }
            })


    }




}