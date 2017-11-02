package com.example.bloder.hamburger.hamburger_details.offers

import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.hamburger_details.offers.calculators.LightCalculator

/**
 * Created by bloder on 01/11/17.
 */
abstract class OfferCalculator {

    abstract fun getTotal(ingredients: List<Ingredient>, partialPrice: Double) : Double

    protected fun totalItemsPaid(qt: Int) : Int = 2 * (qt/3) + (qt % 3)
    protected fun List<Ingredient>.has(name: INGREDIENT_OFFER_TYPE) : Boolean = this.firstOrNull { it.name.trim().toLowerCase() == name.title.trim().toLowerCase() } != null

    protected fun getOfferTotal(ingredients: List<Ingredient>, qtd: Int, offer: OFFER, exception: INGREDIENT_OFFER_TYPE? = null) : Double {
        var total = 0.0
        var index = 0
        returnIngredientListFromOffer(ingredients, offer).forEach {
            if (index < qtd) {
                if (exception != null && exception.title.trim().toLowerCase() == it.name.trim().toLowerCase()) {
                    total = total
                } else {
                    total += it.price
                }
                index++
            }
        }
        return total
    }

    protected fun returnIngredientListFromOffer(ingredients: List<Ingredient>, offer: OFFER) : List<Ingredient> {
        val list = mutableListOf<Ingredient>()
        ingredients.forEach { ingredient ->
            INGREDIENT_OFFER_TYPE.filterByOffer(offer)
                    .filter { it.title.trim().toLowerCase() == ingredient.name.trim().toLowerCase() }
                    .forEach { list.add(ingredient) }
        }
        return list
    }

    protected fun returnIngredientCountFromOffer(ingredients: List<Ingredient>, offer: OFFER, exception: INGREDIENT_OFFER_TYPE? = null) : Int {
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

fun List<Ingredient>.getTotalPrice() : Double {
    var total = 0.0
    OFFER.values().forEach {
        if (it.calculator is LightCalculator) total = it.calculator.getTotal(this, total)
        else total += it.calculator.getTotal(this, total)
    }
    return total
}