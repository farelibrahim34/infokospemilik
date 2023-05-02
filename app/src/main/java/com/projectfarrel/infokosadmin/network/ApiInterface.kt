package com.projectfarrel.infokosadmin.network

import com.projectfarrel.infokosadmin.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {


    @POST("useradmin")
    fun loginAdmin(@Body request : DataClassLoginAdmin):Call<AuthAdminResponse>

    //    Kos Putra
    @GET("datakos")
    fun getAllDataKos(): Call<List<ResponseDataKosItem>>

    @POST("datakos")
    fun addDataKos(@Body request : DataKos) :Call<ResponseDataKos>

    @DELETE("datakos/{id}")
    fun deleteDataKos(@Path("id")id : Int): Call<ResponseDataKosItem>

    @PUT("datakos/{id}")
    fun editDataKos(@Path("id") id: Int, @Body request : DataKos): Call<ResponseDataKosItem>


    //    Kos Putri
    @GET("datakosPutri")
    fun getAllDataKosPutri(): Call<List<ResponseDataKosItem>>

    @POST("datakosPutri")
    fun addDataKosPutri(@Body request : DataKos) :Call<ResponseDataKos>

    @DELETE("datakosPutri/{id}")
    fun deleteDataKosPutri(@Path("id")id : Int): Call<ResponseDataKosItem>

    @PUT("datakosPutri/{id}")
    fun editDataKosPutri(@Path("id") id: Int, @Body request : DataKos): Call<ResponseDataKosItem>

}