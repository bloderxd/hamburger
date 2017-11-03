package com.example.bloder.hamburger.repository.online

import com.example.bloder.hamburger.api.HamburgerApi
import com.example.bloder.hamburger.api.models.Cart
import com.example.bloder.hamburger.api.models.Hamburger
import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.api.payloads.CartExtrasRequestPayload
import com.example.bloder.hamburger.repository.HamburgerRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by bloder on 28/10/17.
 */
class ProductionRepository : HamburgerRepository {

    override fun addToCart(id: Int, extras: List<Int>) : Single<Cart> = HamburgerApi.cartServices()
            .bookHamburger(id, CartExtrasRequestPayload(extras))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .map { it.toModel() }

    override fun getCart(): Single<List<Cart>> = HamburgerApi.cartServices()
            .getCart()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .map { it.map { it.toModel() } }

    override fun addToCart(id: Int): Single<Cart> = HamburgerApi.cartServices()
            .bookHamburger(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .map { it.toModel() }

    override fun getHamburgers() : Single<List<Hamburger>> = HamburgerApi.hamburgerServices()
            .getHamburgers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .map { it.map { it.toModel() } }

    override fun getIngredients() : Single<List<Ingredient>> = HamburgerApi.ingredientServices()
            .getIngredients()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .map { it.map { it.toModel() } }
}