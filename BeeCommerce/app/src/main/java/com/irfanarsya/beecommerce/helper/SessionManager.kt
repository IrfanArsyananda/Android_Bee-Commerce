package com.irfanarsya.beecommerce.helper

import android.content.Context
import android.content.SharedPreferences

class SessionManager(ctx: Context) {

    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var PREF_NAME = "LOGINREGISTER"

    var ISLOGIN = "isLogin"
    var ID = "id"
    var NAME = "nama"
    var EMAIL = "email"

    init {

        pref = ctx.getSharedPreferences(PREF_NAME, 0)
        editor = pref?.edit()

    }

    var login: Boolean?
        get() = pref?.getBoolean(ISLOGIN, false)
        set(login){
            editor?.putBoolean(ISLOGIN, true)
            editor?.commit()
        }

    var id: String?
        get() = pref?.getString(ID, "")
        set(id) {
            editor?.putString(ID, id)
            editor?.commit()
        }

    var nama: String?
        get() = pref?.getString(NAME, "")
        set(nama) {
            editor?.putString(NAME, nama)
            editor?.commit()
        }
    var email: String?
        get() = pref?.getString(EMAIL, "")
        set(email) {
            editor?.putString(EMAIL, email)
            editor?.commit()
        }

    fun logOut(){

        editor?.clear()
        editor?.commit()

    }

}