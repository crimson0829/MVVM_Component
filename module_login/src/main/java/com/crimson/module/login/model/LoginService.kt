package com.crimson.module.login.model

import com.crimson.mvvm.net.RemoteService
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author crimson
 * @date   2020-02-23
 */
interface LoginService :RemoteService {

    /**
     * https://www.wanandroid.com/user/login

    方法：POST
    参数：
    username，password
     */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@FieldMap params: Map<String, String>): Response<ResponseBody>


}