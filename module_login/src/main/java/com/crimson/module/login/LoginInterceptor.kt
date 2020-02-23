package com.crimson.module.login

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.crimson.library.router.api.RouterActivityPath
import com.crimson.library.router.api.RouterInterceptor
import com.crimson.library.router.api.routerPath

/**
 * @author crimson
 * @date 2020/2/21
 * router 登录拦截器 如果未登录，就直接跳转登录页
 */
@Interceptor(priority = 1, name = "登录拦截器")
class LoginInterceptor : IInterceptor {
    override fun process(postcard: Postcard, callback: InterceptorCallback) {
        if (postcard.extra == RouterInterceptor.LOGIN_INTERCEPTOR) {

            //这里还需增加个登录状态判断
            //if (未登录)

            callback.onInterrupt(null)
            //跳转登录页
            routerPath(RouterActivityPath.Login.PAGER_LOGIN)
                .withString("login","interceptor")
                .navigation()
        } else {
            callback.onContinue(postcard)
        }
    }

    override fun init(context: Context) {}
}