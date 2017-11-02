package com.example.bloder.hamburger

import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.hamburger_details.offers.calculators.MuchMeatCalculator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import kotlin.test.assertTrue

/**
 * Created by bloder on 02/11/17.
 */
@RunWith(JUnitPlatform::class)
class MuchMeatOfferTest : Spek({

    given("Much meat offer") {

        it("Should return correct much meat offer price") {
            val ingredients = listOf(
                    Ingredient(0, "Bacon", 1.0, ""),
                    Ingredient(0, "Bacon", 1.0, ""),
                    Ingredient(0, "Bacon", 1.0, ""),
                    Ingredient(0, "Hamburguer de Carne", 1.0, ""),
                    Ingredient(0, "Hamburguer de Carne", 1.0, ""),
                    Ingredient(0, "Hamburguer de Carne", 1.0, "")
            )
            assertTrue { MuchMeatCalculator().getTotal(ingredients, 0.0) == 4.0 }
        }

        it("Should assert that much meat calculator only calculates meats") {
            val ingredients = listOf(
                    Ingredient(0, "Bacon", 1.0, ""),
                    Ingredient(0, "Bacon", 1.0, ""),
                    Ingredient(0, "Bacon", 1.0, ""),
                    Ingredient(0, "Hamburguer de Carne", 1.0, ""),
                    Ingredient(0, "Hamburguer de Carne", 1.0, ""),
                    Ingredient(0, "Hamburguer de Carne", 1.0, ""),
                    Ingredient(0, "Alface", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, "")
            )
            assertTrue { MuchMeatCalculator().getTotal(ingredients, 0.0) == 4.0 }
        }
    }
})