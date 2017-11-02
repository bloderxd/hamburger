package com.example.bloder.hamburger.hamburger_details.offer_controller

import com.example.bloder.hamburger.api.models.Ingredient

/**
 * Created by bloder on 01/11/17.
 */
class OfferCalculator {

    fun getTotal(ingredients: List<Ingredient>) : Double = calcAndGetFiltredTotal(ingredients)

    private fun isLightPromotion(ingredients: List<Ingredient>) : Boolean = ingredients.has(INGREDIENT_OFFER_TYPE.LETTUCE.title) && !ingredients.has(INGREDIENT_OFFER_TYPE.BACON.name)

    private fun calcAndGetFiltredTotal(ingredients: List<Ingredient>) : Double {
        var total = 0.0
        if (isLightPromotion(ingredients)) total += 0.0
        if (ingredients.meatCount() > 0 && !isLightPromotion(ingredients)) total += ingredients.getMeatsTotal(totalItemsPaid(ingredients.meatCount()))
        if (ingredients.meatCount(INGREDIENT_OFFER_TYPE.BACON) > 0 && isLightPromotion(ingredients)) total += ingredients.getMeatsTotal(totalItemsPaid(ingredients.meatCount(INGREDIENT_OFFER_TYPE.BACON)), INGREDIENT_OFFER_TYPE.BACON)
        if (ingredients.cheeseCount() > 0) total += ingredients.getChessesTotal(totalItemsPaid(ingredients.cheeseCount()))
        total += ingredients.getNoOfferTotal()
        return total
    }

    private fun totalItemsPaid(qt: Int) : Int = 2 * (qt/3) + (qt % 3)

    private fun List<Ingredient>.has(name: String) : Boolean = this.firstOrNull { it.name.trim().toLowerCase() == name.trim().toLowerCase() } != null
    private fun List<Ingredient>.meatCount(exception: INGREDIENT_OFFER_TYPE? = null) : Int = returnIngredientCountFromOffer(this, OFFER.MUCH_MEAT, exception)
    private fun List<Ingredient>.cheeseCount() : Int = returnIngredientCountFromOffer(this, OFFER.MUCH_CHEESE)
    private fun List<Ingredient>.getMeatsTotal(qtd: Int, exception: INGREDIENT_OFFER_TYPE? = null) : Double = getOfferTotal(this, OFFER.MUCH_MEAT, exception)
    private fun List<Ingredient>.getChessesTotal(qtd: Int) : Double = getOfferTotal(this, OFFER.MUCH_CHEESE)
    private fun List<Ingredient>.getNoOfferTotal() : Double = getOfferTotal(this, OFFER.NO_OFFER)

    private fun getOfferTotal(ingredients: List<Ingredient>, offer: OFFER, exception: INGREDIENT_OFFER_TYPE? = null) : Double {
        var total = 0.0
        returnIngredientListFromOffer(ingredients, offer).forEach {
            if (exception != null && exception.title.trim().toLowerCase() == it.name.trim().toLowerCase()) {
                total = total
            } else {
                total += it.price
            }
        }
        return total
    }

    private fun returnIngredientListFromOffer(ingredients: List<Ingredient>, offer: OFFER) : List<Ingredient> {
        val list = mutableListOf<Ingredient>()
        ingredients.forEach { ingredient ->
            INGREDIENT_OFFER_TYPE.filterByOffer(offer)
                    .filter { it.name.trim().toLowerCase() == ingredient.name.trim().toLowerCase() }
                    .forEach { list.add(ingredient) }
        }
        return list
    }

    private fun returnIngredientCountFromOffer(ingredients: List<Ingredient>, offer: OFFER, exception: INGREDIENT_OFFER_TYPE? = null) : Int {
        var count = 0
        ingredients.forEach { ingredient ->
            INGREDIENT_OFFER_TYPE.filterByOffer(offer)
                    .filter { it.title.trim().toLowerCase() == ingredient.name.trim().toLowerCase() }
                    .forEach { if (exception != null && exception.title.trim().toLowerCase() == it.name.trim().toLowerCase()) count = count else count++ }
        }
        return count
    }

    enum class INGREDIENT_OFFER_TYPE(val title: String, val offer: OFFER) {
        LETTUCE("Alface", OFFER.LIGHT),
        BACON("Bacon", OFFER.MUCH_MEAT),
        MEAT_HAMBURGER("Hamburguer de Carne", OFFER.MUCH_MEAT),
        CHEESE("Queijo", OFFER.MUCH_CHEESE),
        EGG("Ovo", OFFER.NO_OFFER),
        SESAME_BREAD("Pão com gergelim", OFFER.NO_OFFER);

        companion object Provider {
            fun filterByOffer(offer: OFFER) : List<INGREDIENT_OFFER_TYPE> = INGREDIENT_OFFER_TYPE.values().filter {
                it.offer == offer
            }
        }
    }
}