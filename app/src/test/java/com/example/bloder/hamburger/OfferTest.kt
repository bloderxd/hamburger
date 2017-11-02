package com.example.bloder.hamburger

import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.hamburger_details.offers.getTotalPrice
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
class OfferTest : Spek({

    given("Offers") {

        it("Should return a price without offers") {
            val ingredients = listOf(
                    Ingredient(0, "Alface", 1.0, ""),
                    Ingredient(0, "Bacon", 1.0, ""),
                    Ingredient(0, "Pão com gergelim", 1.0, ""),
                    Ingredient(0, "Ovo", 1.0, ""),
                    Ingredient(0, "Hamburguer de Carne", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, "")
            )
            assertTrue { ingredients.getTotalPrice() == 6.0 }
        }

        it("Should return a price in much meat offer") {
            val ingredients = listOf(
                    Ingredient(0, "Bacon", 1.0, ""),
                    Ingredient(0, "Bacon", 1.0, ""),
                    Ingredient(0, "Hamburguer de Carne", 1.0, ""),
                    Ingredient(0, "Hamburguer de Carne", 1.0, ""),
                    Ingredient(0, "Hamburguer de Carne", 1.0, ""),
                    Ingredient(0, "Alface", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, ""),
                    Ingredient(0, "Pão com gergelim", 1.0, ""),
                    Ingredient(0, "Ovo", 1.0, "")
            )
            assertTrue { ingredients.getTotalPrice() == 8.0 }
        }

        it("Should return a price in much cheese offer") {
            val ingredients = listOf(
                    Ingredient(0, "Bacon", 1.0, ""),
                    Ingredient(0, "Hamburguer de Carne", 1.0, ""),
                    Ingredient(0, "Alface", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, ""),
                    Ingredient(0, "Pão com gergelim", 1.0, ""),
                    Ingredient(0, "Ovo", 1.0, "")
            )
            assertTrue { ingredients.getTotalPrice() == 8.0 }
        }

        it("Should return a price in light offer") {
            val ingredients = listOf(
                    Ingredient(0, "Hamburguer de Carne", 1.0, ""),
                    Ingredient(0, "Hamburguer de Carne", 1.0, ""),
                    Ingredient(0, "Alface", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, ""),
                    Ingredient(0, "Queijo", 1.0, ""),
                    Ingredient(0, "Pão com gergelim", 1.0, ""),
                    Ingredient(0, "Ovo", 1.0, "")
            )
            assertTrue { ingredients.getTotalPrice() == 7.2 }
        }
    }
})