package com.example.bloder.hamburger

import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.hamburger_details.offers.calculators.LightCalculator
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
class LightOfferTest : Spek({

    given("Light offer") {

        it("Should assert that light offer only calculates in light offer case") {
            val ingredients = listOf(
                    Ingredient(0, "Alface", 1.0, ""),
                    Ingredient(0, "Bacon", 1.0, ""),
                    Ingredient(0, "Pão com gergelim", 1.0, ""),
                    Ingredient(0, "Hamburguer de Carne", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, "")
            )
            val lightOfferIngredients = listOf(
                    Ingredient(0, "Alface", 1.0, ""),
                    Ingredient(0, "Pão com gergelim", 1.0, ""),
                    Ingredient(0, "Hamburguer de Carne", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, "")
            )
            assertTrue {
                LightCalculator().getTotal(ingredients, 0.0) == 1.0 && LightCalculator().getTotal(lightOfferIngredients, 9.0) == 9.0
            }
        }
    }
})