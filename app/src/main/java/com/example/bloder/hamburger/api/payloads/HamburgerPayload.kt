package com.example.bloder.hamburger.api.payloads

import com.example.bloder.hamburger.api.models.Hamburger
import com.google.gson.annotations.SerializedName

/**
 * Created by bloder on 28/10/17.
 */
data class HamburgerPayload(
        @SerializedName("id") private val id: Int = -1,
        @SerializedName("name") private val name: String = "",
        @SerializedName("ingredients") private val ingredients: List<Int> = listOf(),
        @SerializedName("image") private val image: String = ""
) {
    fun toModel() : Hamburger = Hamburger(id, name, ingredients, getCorrectImageUrl(image))

    //This is a handling to back end shortened image url's protocol with Picasso
    private fun getCorrectImageUrl(oldUrl: String) : String = if (oldUrl.contains("https")) oldUrl.replace("https", "http") else oldUrl
}