@file:Suppress("LiftReturnOrAssignment")

package com.projectfarrel.infokosadmin.utils

object LoginUtil {
    fun validateUserlogin( username: String, password: String): String {

        var result = ""
        if (username.isEmpty()){
            result =  "please enter username"
        } else {
            result =  "success"
        }

        if(password.isEmpty()){
            result =  "please enter password"
        }  else {
            result =  "success"
        }

        return result


    }
}