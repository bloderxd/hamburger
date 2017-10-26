package com.example.bloder.hamburger.hamburgers

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.example.bloder.hamburger.R
import com.example.bloder.hamburger.hamburgers.redux.HamburgersState
import com.example.bloder.hamburger.redux.ReactView
import com.reduks.reduks.Store
import com.reduks.reduks.reduksStore
import trikita.anvil.Anvil
import trikita.anvil.DSL.*

/**
 * Created by bloder on 25/10/17.
 */
class HamburgersView(private val activity: Context) : ReactView<HamburgersState>(activity) {

    override fun render(state: HamburgersState) {
        xml(R.layout.activity_hamburgers) {

            withId(R.id.hamburgers) {
                val view = Anvil.currentView<RecyclerView>()
                view.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                view.adapter = null
            }

            withId(R.id.cart) {
                onClick {}
            }
        }
    }

    override fun onOptionsItemSelected(state: HamburgersState, item: MenuItem?) {
        val id = item?.itemId

        when(id) {
            R.id.promo -> {}
        }
    }

    override fun menuToInflate(): Int = R.menu.hamburgers_menu
    override fun buildStore(): Store<HamburgersState> = reduksStore {}
}