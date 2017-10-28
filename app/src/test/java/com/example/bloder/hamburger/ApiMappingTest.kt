package com.example.bloder.hamburger

import com.example.bloder.hamburger.api.payloads.CartPayload
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

        it("Should map correct cart object") {
            val cartModel = CartPayload(2, 1, listOf(1, 2), 1509148800).toModel()
            assertTrue {
                cartModel.id == 2
                        && cartModel.hamburgerId == 1
                        && cartModel.extras.isNotEmpty()
                        && cartModel.date.trim().toLowerCase() == "27/10/2017"
            }
        }

    }

})