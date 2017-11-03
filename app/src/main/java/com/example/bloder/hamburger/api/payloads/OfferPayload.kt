package com.example.bloder.hamburger.api.payloads

import com.example.bloder.hamburger.api.models.Offer
import com.google.gson.annotations.SerializedName

/**
 * Created by bloder on 28/10/17.
 */
data class OfferPayload(
        @SerializedName("id") private val id: Int = -1,
        @SerializedName("name") private val name: String = "",
        @SerializedName("description") private val description: String = ""
) {
    fun toModel() : Offer = Offer(id, name, description)
}