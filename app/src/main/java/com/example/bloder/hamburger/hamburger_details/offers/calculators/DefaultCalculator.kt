package com.example.bloder.hamburger.hamburger_details.offers.calculators

import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.hamburger_details.offers.OFFER
import com.example.bloder.hamburger.hamburger_details.offers.OfferCalculator

/**
 * Created by bloder on 01/11/17.
 */
class DefaultCalculator : OfferCalculator() {

    override fun getTotal(ingredients: List<Ingredient>, partialPrice: Double): Double = ingredients.getNoOfferTotal()

    private fun List<Ingredient>.getNoOfferTotal() : Double = getOfferTotal(this, this.size, OFFER.NO_OFFER)
}