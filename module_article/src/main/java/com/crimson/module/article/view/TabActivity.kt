package com.crimson.module.article.view

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.crimson.library.router.api.RouterActivityPath
import com.crimson.module.article.BR
import com.crimson.module.article.R
import com.crimson.module.article.databinding.ActivityTabBinding
import com.crimson.mvvm.base.BaseActivity
import com.crimson.mvvm.binding.adapter.ViewPager2FragmentAdapter
import com.crimson.mvvm.binding.bindAdapter
import com.crimson.mvvm.binding.bindTabLayout
import com.crimson.mvvm.ext.logw
import com.crimson.mvvm.ext.view.toast
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @author crimson
 * @date   2019-12-22
 *  use viewpager2 with tablayout
 *  see: https://developer.android.com/guide/navigation/navigation-swipe-view-2
 */
@Route(path = RouterActivityPath.Article.PAGER_TAB)
class TabActivity : BaseActivity<ActivityTabBinding, TabViewModel>() {


    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_tab
    }

    override fun initViewModelId(): Int {
        return BR.viewModel
    }

    override fun initViewModel(): TabViewModel? {
        return getViewModel()
    }

    override fun initTitleText(): CharSequence? {
        return "欧拉欧拉欧拉"
    }

    override fun isTitleTextCenter(): Boolean {
        return true
    }

    override fun initMenuRes(): Int? {
        return R.menu.tab_menu
    }


    override fun onMenuItemSelected(item: MenuItem) {
        when (item.itemId) {
            R.id.page_refresh -> {
                logw("refresh page")
                toast("refresh page")


            }

        }

    }

    override fun initView() {

        vm?.getData()


    }


    override fun initViewObservable() {

        vm?.tabDataCompleteLD?.observe(this, Observer { it ->

            vb?.viewPager?.apply {

                vm?.fragments?.let {
                    //设置viewpager2 adapter
                    bindAdapter(null, ViewPager2FragmentAdapter(this@TabActivity, it))
                }

                bindTabLayout(vb?.tabLayout, it)


            }


        })


    }


}




