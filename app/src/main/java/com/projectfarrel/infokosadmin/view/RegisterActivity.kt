package com.projectfarrel.infokosadmin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.projectfarrel.infokosadmin.R
import com.projectfarrel.infokosadmin.auth.LoginActivity
import com.projectfarrel.infokosadmin.databinding.ActivityRegisterBinding
import com.projectfarrel.infokosadmin.datastore.DataStoreLogin
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    lateinit var binding : ActivityRegisterBinding
    private lateinit var dataLogin : DataStoreLogin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataLogin = DataStoreLogin(this)


        binding.btnDaftar.setOnClickListener {

            val saveUser = binding.registUser.text.toString()
            val savePw = binding.inputRegistPw.text.toString()

            GlobalScope.launch {
                dataLogin.saveData(saveUser,savePw)
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
        }
    }
}