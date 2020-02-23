package com.crimson.module.login.view

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.crimson.library.router.api.RouterActivityPath
import com.crimson.library.router.api.routerInject
import com.crimson.module.login.BR
import com.crimson.module.login.R
import com.crimson.module.login.databinding.ActivityLoginBinding
import com.crimson.mvvm.base.BaseActivity
import com.crimson.mvvm.ext.view.toast

@Route(path = RouterActivityPath.Login.PAGER_LOGIN)
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    @Autowired(name = "login")
    @JvmField
    var param: String? = ""

    override fun initContentView(savedInstanceState: Bundle?): Int {
        routerInject(this)
        return R.layout.activity_login
    }

    override fun initViewModelId(): Int = BR.viewModel


    override fun initTitleText(): CharSequence? {
        return "登录"
    }

    override fun initView() {


        toast(param)

    }

}

