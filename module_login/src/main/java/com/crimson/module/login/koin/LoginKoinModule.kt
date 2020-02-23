package com.crimson.module.login.koin

import com.crimson.module.login.model.LoginModel
import com.crimson.module.login.model.LoginService
import com.crimson.mvvm.net.NetworkClient
import org.koin.dsl.module

/**
 * @author crimson
 * @date   2020-02-12
 */
val loginModule= module {

    single {
        LoginModel()
    }

    single {
        get<NetworkClient>()
            .obtainRetrofit()
            ?.create(LoginService::class.java)
    }
}