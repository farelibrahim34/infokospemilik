package com.projectfarrel.infokosadmin.auth

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.projectfarrel.infokosadmin.MainActivity
import com.projectfarrel.infokoscalo.R
import com.projectfarrel.infokoscalo.databinding.ActivityLoginBinding
import com.projectfarrel.infokosadmin.datastore.DataStoreLogin
import com.projectfarrel.infokosadmin.utils.LoginUtil
import com.projectfarrel.infokosadmin.view.HomeActivity
import com.projectfarrel.infokosadmin.view.RegisterActivity
import com.projectfarrel.infokosadmin.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var dataLogin : DataStoreLogin
    private lateinit var username : String
    private lateinit var password : String
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)


        dataLogin = DataStoreLogin(this)

        dataLogin.userName.asLiveData().observe(this,{
            username = it.toString()
        })

        dataLogin.userPw.asLiveData().observe(this,{
            password = it.toString()
        })

        binding.btnLogin.setOnClickListener {
            val userName = binding.etUsername.text.toString()
            val Password = binding.etPassword.text.toString()

            if (userName.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"ISI PASSWORD DAN USERNAME ANDA", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }else if (userName == username && Password == password){
                startActivity(Intent(this,HomeActivity::class.java))
                Toast.makeText(this,"Anda Berhasil Login", Toast.LENGTH_SHORT).show()

            }else if (userName != username || Password != password){
                Toast.makeText(this,"USERNAME DAN PASSWORD ANDA SALAH", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }



    }
    private fun login(){
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val validasi = LoginUtil.validateUserlogin(username,password)

            if (validasi == "success"){
                authViewModel.callMasukAdmin(username,password)
                authViewModel.postMasukAdmin().observe(this){
                    if(it != null){
                        Toast.makeText(this,"login sukses", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                    }
                }
            }

        }
    }
    override fun onBackPressed() {

        AlertDialog.Builder(this)
            .setTitle("Tutup Aplikasi")
            .setMessage("Yakin tutup dari aplikasi?")
            .setPositiveButton("Ya"){ dialogInterface: DialogInterface, i: Int ->
                finishAffinity()
            }
            .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }
            .show()


    }

}