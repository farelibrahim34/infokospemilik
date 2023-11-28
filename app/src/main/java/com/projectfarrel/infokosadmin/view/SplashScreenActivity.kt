package com.projectfarrel.infokosadmin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.asLiveData
import com.projectfarrel.infokosadmin.MainActivity
import com.projectfarrel.infokoscalo.R
import com.projectfarrel.infokosadmin.auth.LoginActivity
import com.projectfarrel.infokoscalo.databinding.ActivitySplashScreenBinding
import com.projectfarrel.infokosadmin.datastore.DataStoreLogin

class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding : ActivitySplashScreenBinding
    private lateinit var dataLogin : DataStoreLogin
    private lateinit var usernama : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataLogin = DataStoreLogin(this)

        dataLogin.userName.asLiveData().observe(this,{
            usernama = it.toString()
        })

//        Handler().postDelayed({
//            if (usernama.isEmpty()){
//                startActivity(Intent(this, LoginActivity::class.java))
//                finish()
//            }else{
//                startActivity(Intent(this, HomeActivity::class.java))
//                finish()
//            }
//
//
//        },7000)
        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        },7000)
    }
}