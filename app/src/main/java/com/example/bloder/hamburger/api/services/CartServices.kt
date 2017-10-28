package com.example.bloder.hamburger.api.services

import com.example.bloder.hamburger.api.payloads.CartPayload
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.PUT

/**
 * Created by bloder on 28/10/17.
 */
interface CartServices {

    @GET("api/pedido")
    fun getCart() : Single<List<CartPayload>>

    @PUT("api/pedido/{id}")
    fun bookHamburger() : Single<CartPayload>
}