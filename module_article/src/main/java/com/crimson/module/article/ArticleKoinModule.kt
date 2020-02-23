package com.crimson.module.article

import com.crimson.module.article.model.ArticleService
import com.crimson.module.article.model.AuthorModel
import com.crimson.module.article.view.ArticleAdapter
import com.crimson.module.article.view.AuthorViewModel
import com.crimson.module.article.view.TabViewModel
import com.crimson.mvvm.net.NetworkClient
import org.koin.dsl.module

/**
 * @author crimson
 * @date   2019-12-22
 * you can build any object in module which you want to inject
 * and add the module when application onCreate
 */

val viewModelModule = module {

    factory { TabViewModel() }
    factory { (id: Int) -> AuthorViewModel(id) }


}

val modelModule = module {

    single {
        AuthorModel()
    }

}

val adapterModule = module {

    factory {
        ArticleAdapter()
    }
}

val dataModule = module {

    single {
        get<NetworkClient>()
            .obtainRetrofit()
            ?.create(ArticleService::class.java)
    }
}