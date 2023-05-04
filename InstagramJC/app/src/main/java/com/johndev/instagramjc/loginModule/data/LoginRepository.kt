package com.johndev.instagramjc.loginModule.data

import com.johndev.instagramjc.loginModule.data.network.LoginService

class LoginRepository {

    private val api = LoginService()

    suspend fun doLogin(user: String, password: String) = api.doLogin(user, password)

}