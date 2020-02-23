package com.crimson.mvvm_frame

import android.os.Bundle
import com.crimson.library.router.api.RouterActivityPath
import com.crimson.library.router.api.routerPath
import com.crimson.mvvm.base.BaseActivity
import com.crimson.mvvm.base.BaseViewModel
import com.crimson.mvvm.binding.consumer.bindConsumer
import com.crimson.mvvm_frame.databinding.ActivityMainBinding


/**
 * @author crimson
 * @date   2019-12-31
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }

    override fun initViewModelId(): Int {
        return BR.viewModel
    }

    override fun initTitleText(): CharSequence? {
        return "木大木大木大"
    }

}


class MainViewModel : BaseViewModel() {

    val loginClick = bindConsumer<Unit> {
        routerPath(RouterActivityPath.Login.PAGER_LOGIN)
            //传递参数
            .withString("login", "login")
            .navigation()
    }


    val  checkLoginClick = bindConsumer<Unit> {

        routerPath(RouterActivityPath.Article.PAGER_LOGIN_TEST).navigation()

    }



    val onClickListBtn = bindConsumer<Unit> {
       routerPath(RouterActivityPath.Article.PAGER_TAB).navigation()
    }



}
