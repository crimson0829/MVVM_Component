package com.crimson.module.article.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.crimson.library.router.api.RouterFragmentPath
import com.crimson.module.article.BR
import com.crimson.module.article.R
import com.crimson.module.article.databinding.FragmentTabBinding
import com.crimson.mvvm.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf


/**
 * @author crimson
 * @date   2020-01-02
 * fragment sample
 */
@Route(path = RouterFragmentPath.Article.PAGER_AUTHOR)
class AuthorFragment : BaseFragment<FragmentTabBinding, AuthorViewModel>() {

    override fun initContentView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Int = R.layout.fragment_tab

    override fun initViewModelId(): Int = BR.viewModel

    override fun initViewModel(): AuthorViewModel? {

        arguments?.takeIf { it.containsKey("id") }?.apply {
            val id = getInt("id")
            return getViewModel { parametersOf(id) }
        }

        return null
    }

    override fun initView() {
        vm?.getArticles()

    }


}




