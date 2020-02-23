package com.crimson.library.router.module

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.crimson.library.BuildConfig
import com.crimson.mvvm.ext.logw
import com.crimson.mvvm.module.base.IModule

/**
 * @author crimson
 * @date 2020-02-11
 */
class RouterModule : IModule {

    override fun initKoinModule() {}

    override fun initModule(app: Application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(app)
        logw("ARouter init")
    }
}