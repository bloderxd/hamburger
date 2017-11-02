package com.example.bloder.hamburger.hamburger_details.redux

import com.example.bloder.hamburger.api.models.Hamburger
import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.redux.State

/**
 * Created by bloder on 30/10/17.
 */
data class HamburgerDetailsState(
        val hamburger: Hamburger = Hamburger(-1, "", listOf(), ""),
        val ingredients: List<Ingredient> = listOf()
) : State