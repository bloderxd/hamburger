package com.example.bloder.hamburger.api.models

/**
 * Created by bloder on 28/10/17.
 */
data class Cart(
        val id: Int,
        val hamburgerId: Int,
        val extras: List<Int>,
        val date: String = ""
)