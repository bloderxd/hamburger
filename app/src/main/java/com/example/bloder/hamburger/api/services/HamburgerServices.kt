package com.example.bloder.hamburger.api.services

import com.example.bloder.hamburger.api.payloads.HamburgerPayload
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by bloder on 28/10/17.
 */
interface HamburgerServices {

    @GET("api/lanche")
    fun getHamburgers() : Single<List<HamburgerPayload>>
}