package com.tristanroussel.miaou.service

import com.tristanroussel.miaou.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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
                    .connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(HeaderInterceptor(apiKey))
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()

    fun <T> createService(service: Class<T>): T? =
            if (::retrofit.isInitialized) retrofit.create(service) else null

    @Suppress("UNCHECKED_CAST")
    fun enqueue(call: Call<*>, callback: ((body: Any?, success: Boolean) -> Unit)?) {
        (call as Call<Any>).enqueue(ServiceCallback(callback))
    }

    private class HeaderInterceptor(private val apiKey: String) : Interceptor {

        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            chain.request().run {
                val requestWithHeader = newBuilder()
                        .addHeader("x-api-key", apiKey)
                        .method(method(), body())
                        .build()
                return chain.proceed(requestWithHeader)
            }
        }
    }

    private class ServiceCallback(val callback: ((body: Any?, success: Boolean) -> Unit)?) : Callback<Any> {

        override fun onResponse(call: Call<Any>, response: Response<Any>) {
            callback?.invoke(response.body(), response.isSuccessful)
        }

        override fun onFailure(call: Call<Any>, t: Throwable) {
            if (!call.isCanceled) {
                callback?.invoke(null, false)
            }
        }
    }
}