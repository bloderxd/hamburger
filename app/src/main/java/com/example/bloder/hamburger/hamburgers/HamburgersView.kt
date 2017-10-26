package com.example.bloder.hamburger.hamburgers

import android.content.Context
import com.example.bloder.hamburger.hamburgers.redux.HamburgersState
import com.example.bloder.hamburger.redux.ReactView
import com.reduks.reduks.Store
import com.reduks.reduks.reduksStore

/**
 * Created by bloder on 25/10/17.
 */
class HamburgersView(context: Context) : ReactView<HamburgersState>(context) {

    override fun render(state: HamburgersState) {}

    override fun buildStore(): Store<HamburgersState> = reduksStore {}
}