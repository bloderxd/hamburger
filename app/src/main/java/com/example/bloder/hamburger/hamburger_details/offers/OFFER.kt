package com.example.bloder.hamburger.hamburger_details.offers

import com.example.bloder.hamburger.hamburger_details.offers.calculators.DefaultCalculator
import com.example.bloder.hamburger.hamburger_details.offers.calculators.LightCalculator
import com.example.bloder.hamburger.hamburger_details.offers.calculators.MuchCheeseCalculator
import com.example.bloder.hamburger.hamburger_details.offers.calculators.MuchMeatCalculator

/**
 * Created by bloder on 01/11/17.
 */
enum class OFFER(val calculator: OfferCalculator) {
    MUCH_MEAT(MuchMeatCalculator()),
    MUCH_CHEESE(MuchCheeseCalculator()),
    NO_OFFER(DefaultCalculator()),
    LIGHT(LightCalculator())
}