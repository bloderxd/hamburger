package com.example.bloder.hamburger.offer.redux

import com.example.bloder.hamburger.api.models.Offer
import com.example.bloder.hamburger.redux.State

/**
 * Created by bloder on 02/11/17.
 */
data class OfferState(val offers: List<Offer> = listOf()) : State