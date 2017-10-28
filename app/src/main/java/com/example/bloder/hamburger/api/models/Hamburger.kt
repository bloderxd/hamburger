package com.example.bloder.hamburger.api.models

/**
 * Created by bloder on 28/10/17.
 */
data class Hamburger(
        private val id: Int,
        private val name: String,
        private val ingredients: List<Int>,
        private val image: String
)