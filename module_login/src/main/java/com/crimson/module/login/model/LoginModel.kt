package com.crimson.module.login.model

import com.crimson.mvvm.base.BaseModel
import org.koin.core.inject

/**
 * @author crimson
 * @date   2020-02-23
 */
class LoginModel : BaseModel() {

    val service: LoginService by inject()

    suspend fun login(userName: String, password: String) = service.login(
        mapOf(
            "username" to userName,
            "password" to password
            )
    )

}