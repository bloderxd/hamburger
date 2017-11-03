package com.example.bloder.hamburger.repository

import com.example.bloder.hamburger.api.models.Cart
import com.example.bloder.hamburger.api.models.Hamburger
import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.api.models.Offer
import com.example.bloder.hamburger.repository.local.LocalRepository
import com.example.bloder.hamburger.repository.online.ProductionRepository
import io.reactivex.Single

/**
 * Created by bloder on 28/10/17.
 */
interface HamburgerRepository {

    companion object {
        fun get(type: REPOSITORY_ENVIRONMENT): HamburgerRepository = type.reference
    }

    fun getHamburgers() : Single<List<Hamburger>>
    fun getIngredients() : Single<List<Ingredient>>
    fun getCart() : Single<List<Cart>>
    fun addToCart(id: Int) : Single<Cart>
    fun addToCart(id: Int, extras: List<Int>) : Single<Cart>
    fun getOffers() : Single<List<Offer>>
}

enum class REPOSITORY_ENVIRONMENT(val reference: HamburgerRepository) { PROD(ProductionRepository()), LOCAL(LocalRepository()) }