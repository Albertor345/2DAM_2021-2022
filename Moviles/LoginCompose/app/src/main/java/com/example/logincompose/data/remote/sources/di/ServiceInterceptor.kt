package com.example.logincompose.data.remote.sources.di

import okhttp3.Interceptor
import okhttp3.Response


class ServiceInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder()
            .addQueryParameter("api_key", "93f41d188d41bbe2f0586615434bb847")
            .build()
        val request = chain.request().newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}
