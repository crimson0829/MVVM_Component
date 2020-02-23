package com.crimson.module.login

import android.app.Application
import com.crimson.module.login.koin.loginModule
import com.crimson.mvvm.module.base.IModule
import com.crimson.mvvm.module.injectKoinModules

/**
 * @author crimson
 * @date   2020-02-12
 */
class LoginModule : IModule {

    override fun initKoinModule() {

        injectKoinModules(loginModule)

    }

    override fun initModule(app: Application) {
    }
}