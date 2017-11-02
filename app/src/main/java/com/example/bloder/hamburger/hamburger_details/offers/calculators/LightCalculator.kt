package com.example.bloder.hamburger.hamburger_details.offers.calculators

import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.hamburger_details.offers.OFFER
import com.example.bloder.hamburger.hamburger_details.offers.OfferCalculator

/**
 * Created by bloder on 01/11/17.
 */
class LightCalculator : OfferCalculator() {

    override fun getTotal(ingredients: List<Ingredient>, partialPrice: Double): Double =
            if (isLightOffer(ingredients)) (ingredients.getLightTotal() + partialPrice) - ((ingredients.getLightTotal() + partialPrice) * 0.1) else ingredients.getLightTotal() + partialPrice

    private fun List<Ingredient>.getLightTotal(exception: INGREDIENT_OFFER_TYPE? = null) : Double = getOfferTotal(this, this.size, OFFER.LIGHT, exception)
    private fun isLightOffer(ingredients: List<Ingredient>): Boolean = ingredients.has(INGREDIENT_OFFER_TYPE.LETTUCE) && !ingredients.has(INGREDIENT_OFFER_TYPE.BACON)
}