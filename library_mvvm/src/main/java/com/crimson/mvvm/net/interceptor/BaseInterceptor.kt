package com.crimson.mvvm.net.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * 基础拦截器
 */
class BaseInterceptor(private val headers: Map<String, String>?) :
    Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if (headers != null && headers.isNotEmpty()) {
            val keys = headers.keys
            for (headerKey in keys) {
                builder.addHeader(headerKey, headers[headerKey] ?: error("")).build()
            }
        }
        //请求信息
        return chain.proceed(builder.build())
    }

}