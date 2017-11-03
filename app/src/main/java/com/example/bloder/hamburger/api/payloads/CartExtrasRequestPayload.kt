package com.example.bloder.hamburger.api.payloads

import com.google.gson.annotations.SerializedName

/**
 * Created by bloder on 02/11/17.
 */
data class CartExtrasRequestPayload(
        @SerializedName("extras") val extras: List<Int>
)