package com.tristanroussel.miaou.service

import com.tristanroussel.miaou.model.Breed
import com.tristanroussel.miaou.utils.Routes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

object CatService {

    internal interface CatClient {

        @GET(Routes.breeds)
        fun getBreeds(
                @Query("page") page: Int,
                @Query("limit") limit: Int
        ): Call<List<Breed>>
    }

    fun getBreeds(callback: ((body: Any?, success: Boolean) -> Unit)) {
        Service.createService(CatClient::class.java)?.let {
            Service.enqueue(it.getBreeds(0, 5), callback)
        }
    }
}