package com.tristanroussel.miaou.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Service {

    private lateinit var retrofit: Retrofit

    fun configRetrofit(baseUrl: String, apiKey: String) {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createRetrofitClient(apiKey))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createRetrofitClient(apiKey: String): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.request().run {
                    val requestWithHeader = newBuilder()
                        .addHeader("x-api-key", apiKey)
                        .method(method(), body())
                        .build()
                    chain.proceed(requestWithHeader)
                }
            }
            .build()
}