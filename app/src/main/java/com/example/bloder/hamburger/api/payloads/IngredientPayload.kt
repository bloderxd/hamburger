package com.example.bloder.hamburger.api.payloads

import com.example.bloder.hamburger.api.models.Ingredient
import com.google.gson.annotations.SerializedName

/**
 * Created by bloder on 28/10/17.
 */
data class IngredientPayload(
        @SerializedName("id") private val id: Int = -1,
        @SerializedName("name") private val name: String = "",
        @SerializedName("price") private val price: Double = 0.0,
        @SerializedName("image") private val image: String = ""
) {
    fun toModel() : Ingredient = Ingredient(id, name, price, image)
}