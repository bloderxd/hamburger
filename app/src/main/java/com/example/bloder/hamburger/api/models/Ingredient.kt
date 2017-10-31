package com.example.bloder.hamburger.api.models

import java.io.Serializable

/**
 * Created by bloder on 28/10/17.
 */
data class Ingredient(
        val id: Int,
        val name: String,
        val price: Double,
        val image: String
) : Serializable