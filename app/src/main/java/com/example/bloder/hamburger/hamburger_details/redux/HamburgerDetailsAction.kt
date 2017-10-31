package com.example.bloder.hamburger.hamburger_details.redux

import com.example.bloder.hamburger.api.models.Hamburger
import com.reduks.reduks.Action

/**
 * Created by bloder on 30/10/17.
 */
sealed class HamburgerDetailsAction : Action<HamburgerDetailsState> {

    class UpdateDetail(private val hamburger: Hamburger) : HamburgerDetailsAction() {
        override fun action(state: HamburgerDetailsState): HamburgerDetailsState = HamburgerDetailsState(hamburger)
    }
}