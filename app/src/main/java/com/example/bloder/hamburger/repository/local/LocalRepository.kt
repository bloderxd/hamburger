package com.example.bloder.hamburger.repository.local

import com.example.bloder.hamburger.api.models.Hamburger
import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.repository.HamburgerRepository
import io.reactivex.Single

/**
 * Created by bloder on 28/10/17.
 */
class LocalRepository : HamburgerRepository {

    override fun getHamburgers() : Single<List<Hamburger>> = Single.create {
        listOf(
                Hamburger(1, "X-Bacon", listOf(2, 3, 5, 6), "https://goo.gl/W9WdaF"),
                Hamburger(2, "X-Burger", listOf(3, 5, 6), "https://goo.gl/Cjfxi9")
        )
    }

    override fun getIngredients() : Single<List<Ingredient>> = Single.create {
        listOf(
                Ingredient(1, "Alface", 0.4, "https://goo.gl/9DhCgk"),
                Ingredient(2, "Bacon", 2.0, "https://goo.gl/8qkVH0")
        )
    }
}