package com.example.bloder.hamburger.api.payloads

import com.example.bloder.hamburger.api.models.Cart
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by bloder on 28/10/17.
 */
data class CartPayload(
        @SerializedName("id") private val id: Int = -1,
        @SerializedName("id_sandwich") private val hamburgerId: Int = -1,
        @SerializedName("extras") private val extras: List<Int> = listOf(),
        @SerializedName("date") private val date: Long = 0L
) {
    fun toModel() : Cart = Cart(
            id,
            hamburgerId,
            extras,
            SimpleDateFormat("dd/MM/yyyy").format(Date(date*1000))
    )
}