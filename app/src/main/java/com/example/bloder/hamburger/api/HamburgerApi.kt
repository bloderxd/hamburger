package com.example.bloder.hamburger.api

import com.example.bloder.hamburger.BuildConfig
import com.example.bloder.hamburger.api.services.CartServices
import com.example.bloder.hamburger.api.services.HamburgerServices
import com.example.bloder.hamburger.api.services.IngredientServices
import com.example.bloder.hamburger.api.services.OfferServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by bloder on 28/10/17.
 */
object HamburgerApi {

    private fun getRetrofitConfig() : Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.HAMBURGER_ENDPOINT)
            .client(OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    fun hamburgerServices() : HamburgerServices = getRetrofitConfig().create(HamburgerServices::class.java)
    fun cartServices() : CartServices = getRetrofitConfig().create(CartServices::class.java)
    fun ingredientServices() : IngredientServices = getRetrofitConfig().create(IngredientServices::class.java)
    fun offerServices() : OfferServices = getRetrofitConfig().create(OfferServices::class.java)
}