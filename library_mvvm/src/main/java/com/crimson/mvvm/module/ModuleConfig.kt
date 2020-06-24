package com.crimson.mvvm.module

import android.app.Application
import com.crimson.mvvm.ext.logw
import com.crimson.mvvm.ext.tryCatch
import com.crimson.mvvm.module.base.IModule

/**
 * @author crimson
 * @date   2020-02-10
 * 组件配置
 * 每个需要初始化的组件必须申明完成路径并加入到 modules 集合中，方便整体组件的管理
 */
object ModuleConfig {

    //基础组件
    private const val BASE_MODULE = "com.crimson.mvvm.module.base.BaseModule"

    //router组件
    private const val ROUTER_MODULE = "com.crimson.library.router.module.RouterModule"

    //login 组件
    private const val LOGIN_MODULE = "com.crimson.module.login.LoginModule"

    //Article 租价
    private const val ARTICLE_MODULE = "com.crimson.module.article.ArticleModule"


    //组件集合
    private var modules =
        arrayListOf(
            BASE_MODULE,
            ROUTER_MODULE,
            LOGIN_MODULE,
            ARTICLE_MODULE

        )

    /**
     * 对各组件的Koin组件初始化
     */
    @JvmStatic
    fun initKoinModule() {

        modules.forEach {
            tryCatch {
                val clazz = Class.forName(it)
                val module = clazz.newInstance() as? IModule
                module?.initKoinModule()
            }
        }

    }

    /**
     * 初始化组件
     */
    @JvmStatic
    fun initModule(app: Application) {

        modules.forEach {
            tryCatch {
                logw("module init -> $it")
                val clazz = Class.forName(it)
                val module = clazz.newInstance() as? IModule
                module?.initModule(app)

            }

        }

    }
}



