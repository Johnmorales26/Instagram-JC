package com.johndev.instagramjc.loginModule.data

import com.johndev.instagramjc.loginModule.data.network.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginService: LoginService) {

    suspend fun doLogin(user: String, password: String) = loginService.doLogin(user, password)

}