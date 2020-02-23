package com.crimson.module.article.model

import com.crimson.module.article.model.kdo.AuthorListEntity
import com.crimson.module.article.model.kdo.TabListEntity
import com.crimson.mvvm.net.RemoteService
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author crimson
 * @date   2019-12-22
 * retrofit service
 */
interface ArticleService : RemoteService {

    //协程
    @GET("wxarticle/chapters/json")
    suspend fun getTab(): Response<TabListEntity>

    //rxjava
    @GET("wxarticle/list/{id}/{page}/json")
    fun getArticles(
        @Path("id") id: Int,
        @Path("page") page: Int

    ): Flowable<Response<AuthorListEntity>>
}