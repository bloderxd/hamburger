package com.example.bloder.hamburger

import com.example.bloder.hamburger.api.payloads.CartPayload
import com.example.bloder.hamburger.api.payloads.HamburgerPayload
import com.example.bloder.hamburger.api.payloads.IngredientPayload
import com.example.bloder.hamburger.api.payloads.OfferPayload
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import kotlin.test.assertTrue

/**
 * Created by bloder on 28/10/17.
 */

@RunWith(JUnitPlatform::class)
class ApiMappingTest : Spek({

    given("Payloads to model") {

        it("Should map correct Cart object") {
            val model = CartPayload(2, 1, listOf(1, 2), 1509148800).toModel()
            assertTrue {
                model.id == 2
                        && model.hamburgerId == 1
                        && model.extras.isNotEmpty()
                        && model.date.trim().toLowerCase() == "27/10/2017"
            }
        }

        it("Should map correct Hamburger object") {
            val model = HamburgerPayload(0, "bacon", listOf(), "image").toModel()
            assertTrue {
                model.id == 0
                        && model.name.trim().toLowerCase() == "bacon"
                        && model.ingredients.isEmpty()
                        && model.image.trim().toLowerCase() == "image"
            }
        }

        it("Should map correct Ingredient object") {
            val model = IngredientPayload(1, "BBQ", 1.5, "image").toModel()
            assertTrue {
                model.id == 1
                        && model.name.trim().toLowerCase() == "bbq"
                        && model.price == 1.5
                        && model.image.trim().toLowerCase() == "image"
            }
        }

        it("Should map correct Offer object") {
            val model = OfferPayload(2, "this is a description").toModel()
            assertTrue { model.id == 2 && model.description.trim().toLowerCase() == "this is a description" }
        }

    }

})