package com.crimson.module.article.view

import android.os.Bundle
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.alibaba.android.arouter.facade.annotation.Route
import com.crimson.library.router.api.RouterActivityPath
import com.crimson.library.router.api.RouterInterceptor
import com.crimson.module.article.R
import com.crimson.module.article.databinding.ActivityPictureBinding
import com.crimson.mvvm.base.BaseActivity
import com.crimson.mvvm.base.BaseViewModel
import com.crimson.mvvm.binding.bindClick
import com.crimson.mvvm.binding.bindImage
import com.crimson.mvvm.binding.consumer.bindConsumer
import kotlinx.android.synthetic.main.activity_picture.*

/**
 * @author crimson
 * @date   2020-01-03
 * 测试登录拦截器
 */
@Route(path = RouterActivityPath.Article.PAGER_LOGIN_TEST,extras = RouterInterceptor.LOGIN_INTERCEPTOR)
class TestLoginInterceptorActivity : BaseActivity<ActivityPictureBinding, BaseViewModel>() {

    val url = "https://img.xjh.me/random_img.php?type=bg&ctype=nature&return=302&device=mobile"

    override fun initContentView(savedInstanceState: Bundle?): Int =
        R.layout.activity_picture

    override fun initViewModelId(): Int = 0


    override fun initView() {

        btn_change.bindClick(bindConsumer {

            MaterialDialog(context).show {
                listItems(R.array.imageStyles) { _, index, _ ->
                    val style = index + 1
                    bindImage(style)
                    dismiss()
                }
            }

        }, 0)


        bindImage()

    }

    private fun bindImage(style: Int = 2) {
        vb?.ivPicture?.bindImage(
            url,
            style, 5, true, 4, R.drawable.icon_picture
        )
    }

}