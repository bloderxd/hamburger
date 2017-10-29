package com.example.bloder.hamburger.hamburgers.redux

import com.example.bloder.hamburger.api.models.Hamburger
import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.redux.State

/**
 * Created by bloder on 25/10/17.
 */
data class HamburgersState(val hamburgers: List<Hamburger> = listOf(), val ingredients: List<Ingredient> = listOf()) : State