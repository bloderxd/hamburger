package com.example.bloder.hamburger.offer.redux

import com.example.bloder.hamburger.api.models.Offer
import com.reduks.reduks.Action

/**
 * Created by bloder on 02/11/17.
 */
sealed class OfferActions : Action<OfferState> {

    class FetchOffers(private val funktion: () -> Any?) : OfferActions() {
        override fun action(state: OfferState): OfferState {
            funktion()
            return state
        }
    }

    class OnOffersFetched(private val offers: List<Offer>) : OfferActions() {
        override fun action(state: OfferState): OfferState = OfferState(offers)
    }
}