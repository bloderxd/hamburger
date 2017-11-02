package com.example.bloder.hamburger.hamburger_details.offers.calculators

import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.hamburger_details.offers.OFFER
import com.example.bloder.hamburger.hamburger_details.offers.OfferCalculator

/**
 * Created by bloder on 01/11/17.
 */
class MuchCheeseCalculator : OfferCalculator() {

    override fun getTotal(ingredients: List<Ingredient>, partialPrice: Double): Double {
        var total = 0.0
        if (ingredients.cheeseCount() > 0) total += ingredients.getChessesTotal(totalItemsPaid(ingredients.cheeseCount()))
        return total
    }

    private fun List<Ingredient>.cheeseCount() : Int = returnIngredientCountFromOffer(this, OFFER.MUCH_CHEESE)
    private fun List<Ingredient>.getChessesTotal(qtd: Int) : Double = getOfferTotal(this, qtd, OFFER.MUCH_CHEESE)
}