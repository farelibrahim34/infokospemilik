package com.projectfarrel.infokosadmin.network

import com.projectfarrel.infokosadmin.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @POST("useradmin")
    fun loginAdmin(@Body request : DataClassLoginAdmin):Call<AuthAdminResponse>

    @GET("datakos")
    fun getAllDataKos(): Call<List<ResponseDataKosItem>>

    @POST("datakos")
    fun addDataKos(@Body request : DataKos) :Call<ResponseDataKos>
}