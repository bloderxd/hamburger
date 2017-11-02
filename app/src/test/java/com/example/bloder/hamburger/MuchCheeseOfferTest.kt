package com.example.bloder.hamburger

import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.hamburger_details.offers.calculators.MuchCheeseCalculator
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
class MuchCheeseOfferTest : Spek({

    given("Much cheese offer") {

        it("Should return correct much cheese offer ingredients price") {
            val ingredients = listOf(
                    Ingredient(0, "Queijo", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, "")
            )
            assertTrue { MuchCheeseCalculator().getTotal(ingredients, 0.0) == 4.0 }
        }

        it("Should assert that much cheese calculator only calculates cheeses") {
            val ingredients = listOf(
                    Ingredient(0, "Queijo", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, ""),
                    Ingredient(0, "Bacon", 1.0, ""),
                    Ingredient(0, "Pão com gergelim", 1.0, ""),
                    Ingredient(0, "Hamburguer de Carne", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, "")
            )
            assertTrue { MuchCheeseCalculator().getTotal(ingredients, 0.0) == 4.0 }
        }
    }
})