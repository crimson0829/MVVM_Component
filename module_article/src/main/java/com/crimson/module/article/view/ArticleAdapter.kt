package com.crimson.module.article.view

import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.crimson.module.article.R
import com.crimson.module.article.databinding.AdapterItemArticleBinding
import com.crimson.module.article.model.kdo.ArticleEntity
import com.crimson.mvvm.binding.adapter.BaseBindingAdapter

/**
 * @author crimson
 * @date   2019-12-30
 * a adapter sample
 */
class ArticleAdapter : BaseBindingAdapter<ArticleEntity, AdapterItemArticleBinding>
    (R.layout.adapter_item_article) {

    override fun convert(helper: BaseViewHolder, item: ArticleEntity?) {
        helper.getBinding<AdapterItemArticleBinding>()?.model = item
        super.convert(helper, item)

    }


}