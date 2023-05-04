package com.johndev.instagramjc.loginModule.domain

import com.johndev.instagramjc.loginModule.data.LoginRepository

class LoginUseCase {

    val repository = LoginRepository()

    suspend operator fun invoke(user: String, password: String): Boolean {
        return repository.doLogin(user, password)
    }

}