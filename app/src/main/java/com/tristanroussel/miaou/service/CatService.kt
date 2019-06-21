package com.tristanroussel.miaou.service

import com.tristanroussel.miaou.model.Breed
import com.tristanroussel.miaou.model.BreedImage
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

        @GET(Routes.breedImages)
        fun getBreedImages(
                @Query("breed_id") breedId: String,
                @Query("limit") limit: Int,
                @Query("size") size: String
        ): Call<List<BreedImage>>
    }

    fun getBreeds(page: Int, callback: ((body: Any?, success: Boolean) -> Unit)) {
        Service.createService(CatClient::class.java)?.let {
            Service.enqueue(it.getBreeds(page, 15), callback)
        }
    }

    fun getBreedImages(breedId: String, callback: ((body: Any?, success: Boolean) -> Unit)) {
        Service.createService(CatClient::class.java)?.let {
            Service.enqueue(it.getBreedImages(breedId, 5, "small"), callback)
        }
    }
}