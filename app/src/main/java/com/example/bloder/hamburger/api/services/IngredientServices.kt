package com.example.bloder.hamburger.api.services

import com.example.bloder.hamburger.api.payloads.IngredientPayload
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by bloder on 28/10/17.
 */
interface IngredientServices {

    @GET("api/ingrediente")
    fun getIngredients() : Single<List<IngredientPayload>>
}