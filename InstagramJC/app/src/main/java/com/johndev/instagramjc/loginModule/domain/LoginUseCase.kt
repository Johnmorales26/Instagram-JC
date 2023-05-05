package com.johndev.instagramjc.loginModule.domain

import com.johndev.instagramjc.loginModule.data.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(user: String, password: String): Boolean {
        return loginRepository.doLogin(user, password)
    }

}