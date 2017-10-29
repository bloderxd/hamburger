package com.example.bloder.hamburger.hamburgers.redux

import com.example.bloder.hamburger.api.models.Hamburger
import com.example.bloder.hamburger.api.models.Ingredient
import com.reduks.reduks.Action

/**
 * Created by bloder on 28/10/17.
 */
sealed class HamburgersAction : Action<HamburgersState> {

    object FetchHamburgers : HamburgersAction()
    object FetchIngredients : HamburgersAction()
    class HamburgersFetched(val hamburgers: List<Hamburger>) : HamburgersAction()
    class IngredientsFetched(val ingredients: List<Ingredient>) : HamburgersAction()
}