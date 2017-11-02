package com.example.bloder.hamburger.hamburger_details.offers.calculators

import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.hamburger_details.offers.OFFER
import com.example.bloder.hamburger.hamburger_details.offers.OfferCalculator

/**
 * Created by bloder on 01/11/17.
 */
class MuchMeatCalculator : OfferCalculator() {

    override fun getTotal(ingredients: List<Ingredient>, partialPrice: Double): Double {
        var total = 0.0
        if (ingredients.meatCount() > 0 && !isLightOffer(ingredients)) total += ingredients.getMeatsTotal(totalItemsPaid(ingredients.meatCount()))
        else if (ingredients.meatCount(INGREDIENT_OFFER_TYPE.BACON) > 0 && isLightOffer(ingredients)) total += ingredients.getMeatsTotal(totalItemsPaid(ingredients.meatCount(INGREDIENT_OFFER_TYPE.BACON)), INGREDIENT_OFFER_TYPE.BACON)
        return total
    }

    private fun isLightOffer(ingredients: List<Ingredient>): Boolean = ingredients.has(INGREDIENT_OFFER_TYPE.LETTUCE) && !ingredients.has(INGREDIENT_OFFER_TYPE.BACON)
    private fun List<Ingredient>.meatCount(exception: INGREDIENT_OFFER_TYPE? = null) : Int = returnIngredientCountFromOffer(this, OFFER.MUCH_MEAT, exception)
    private fun List<Ingredient>.getMeatsTotal(qtd: Int, exception: INGREDIENT_OFFER_TYPE? = null) : Double = getOfferTotal(this, qtd, OFFER.MUCH_MEAT, exception)
}