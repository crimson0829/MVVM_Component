package com.crimson.module.article.view

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.crimson.library.router.api.RouterActivityPath
import com.crimson.library.router.api.routerInject
import com.crimson.module.article.BR
import com.crimson.module.article.R
import com.crimson.module.article.databinding.ActivityTabBinding
import com.crimson.mvvm.base.BaseActivity
import com.crimson.mvvm.binding.adapter.ViewPager2FragmentAdapter
import com.crimson.mvvm.binding.bindAdapter
import com.crimson.mvvm.binding.bindTabLayout
import com.crimson.mvvm.binding.bindViewPager2
import com.crimson.mvvm.ext.logw
import com.crimson.mvvm.ext.view.toast
import kotlinx.android.synthetic.main.activity_tab.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @author crimson
 * @date   2019-12-22
 *  use viewpager2 with tablayout
 *  see: https://developer.android.com/guide/navigation/navigation-swipe-view-2
 */
@Route(path = RouterActivityPath.Article.PAGER_TAB)
class TabActivity : BaseActivity<ActivityTabBinding, TabViewModel>() {

    @Autowired(name = "param")
    @JvmField
    var params:String?=""


    override fun initContentView(savedInstanceState: Bundle?): Int {
        routerInject(this)
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

        toast(params)

        vm?.getData()


    }


    override fun initViewObservable() {


        vm?.tabDataCompleteLD?.observe(this, Observer {

            tab_layout.bindViewPager2(view_pager, this, vm?.fragments, it)

            (view_pager.getChildAt(0) as? RecyclerView)?.setItemViewCacheSize(
                vm?.fragments?.size ?: 4
            )

        })


    }


}




