package com.example.bloder.hamburger.repository.online

import com.example.bloder.hamburger.api.HamburgerApi
import com.example.bloder.hamburger.api.models.Hamburger
import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.repository.HamburgerRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by bloder on 28/10/17.
 */
class ProductionRepository : HamburgerRepository {

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