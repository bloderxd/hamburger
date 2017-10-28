package com.example.bloder.hamburger.api.models

/**
 * Created by bloder on 28/10/17.
 */
data class Hamburger(
        val id: Int,
        val name: String,
        val ingredients: List<Int>,
        val image: String
)