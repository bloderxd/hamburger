package com.example.bloder.hamburger

import com.example.bloder.hamburger.repository.HamburgerRepository
import com.example.bloder.hamburger.repository.REPOSITORY_ENVIRONMENT
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

/**
 * Created by bloder on 28/10/17.
 */
@RunWith(JUnitPlatform::class)
class RepositoryTest : Spek({

    given("App repository") {
        val repository by lazy { HamburgerRepository.get(REPOSITORY_ENVIRONMENT.LOCAL) }

        it("Should return a completed hamburger list") {
            repository.getHamburgers().toFlowable().test().assertValue {
                it.isNotEmpty()
            }
        }

        it("Should return a completed ingredient list") {
            repository.getIngredients().toFlowable().test().assertValue {
                it.isNotEmpty()
            }
        }
    }

})