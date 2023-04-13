package com.projectfarrel.infokosadmin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projectfarrel.infokosadmin.model.AuthAdminResponse
import com.projectfarrel.infokosadmin.model.DataClassLoginAdmin
import com.projectfarrel.infokosadmin.network.ApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel@Inject constructor(private val api : ApiInterface): ViewModel() {

    lateinit var masukAdmin : MutableLiveData<AuthAdminResponse?>

    init {
        masukAdmin = MutableLiveData()
    }
    fun postMasukAdmin(): MutableLiveData<AuthAdminResponse?> {
        return masukAdmin
    }


    fun callMasukAdmin(username : String, password : String){
        api.loginAdmin(DataClassLoginAdmin(username,password))
            .enqueue(object : Callback<AuthAdminResponse>{
                override fun onResponse(
                    call: Call<AuthAdminResponse>,
                    response: Response<AuthAdminResponse>
                ) {
                    if (response.isSuccessful){
                        val body= response.body()
                        if (body != null){
                            masukAdmin.postValue(body)
                        }else{
                            masukAdmin.postValue(null)
                            println("body null")
                        }
                    }else{
                        masukAdmin.postValue(null)
                        println("Not Success -> ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<AuthAdminResponse>, t: Throwable) {
                    masukAdmin.postValue(null)
                    println("Server Error")
                }

            })
    }

}