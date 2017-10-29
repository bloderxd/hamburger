package com.example.bloder.hamburger.api.services

import com.example.bloder.hamburger.api.payloads.OfferPayload
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by bloder on 28/10/17.
 */
interface OfferServices {

    @GET("api/promocao")
    fun getOffers() : Single<List<OfferPayload>>
}