package com.example.bloder.hamburger.hamburgers

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.example.bloder.hamburger.R
import com.example.bloder.hamburger.hamburgers.redux.HamburgersAction
import com.example.bloder.hamburger.hamburgers.redux.HamburgersState
import com.example.bloder.hamburger.redux.ReactView
import com.example.bloder.hamburger.repository.HamburgerRepository
import com.example.bloder.hamburger.repository.REPOSITORY_ENVIRONMENT
import com.reduks.reduks.Store
import com.reduks.reduks.reduksStore
import trikita.anvil.Anvil
import trikita.anvil.DSL.*

/**
 * Created by bloder on 25/10/17.
 */
class HamburgersView(private val activity: Context) : ReactView<HamburgersState>(activity) {

    private val repository by lazy { HamburgerRepository.get(REPOSITORY_ENVIRONMENT.PROD) }

    override fun render(state: HamburgersState) {
        xml(R.layout.activity_hamburgers) {

            withId(R.id.progress) {
                visibility(state.hamburgers.isEmpty())
            }

            withId(R.id.hamburgers) {
                val view = Anvil.currentView<RecyclerView>()
                val adapter = if (view.adapter == null && state.hamburgers.isNotEmpty()) HamburgersAdapter(activity, state.hamburgers, state.ingredients) else view.adapter
                view.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                view.adapter = adapter
            }

            if (state.hamburgers.isEmpty() && state.ingredients.isEmpty()) fetchFood()
        }
    }

    private fun fetchFood() = repository.getIngredients().subscribe { ingredients, _ ->
        dispatch(HamburgersAction.IngredientsFetched(ingredients))
        dispatch(HamburgersAction.FetchHamburgers)
    }

    private fun fetchHamburgers() = repository.getHamburgers().subscribe { hamburgers, _ ->
        dispatch(HamburgersAction.HamburgersFetched(hamburgers))
    }

    private fun fetchIngredients() = repository.getIngredients().subscribe { ingredients ->
        dispatch(HamburgersAction.IngredientsFetched(ingredients))
    }

    override fun onOptionsItemSelected(state: HamburgersState, item: MenuItem?) {
        val id = item?.itemId
        when(id) {
            R.id.promo -> {}
        }
    }

    override fun menuToInflate(): Int = R.menu.hamburgers_menu

    override fun buildStore(): Store<HamburgersState> = reduksStore {
        initialReducer = { state, action ->
            when (action) {
                is HamburgersAction.HamburgersFetched -> HamburgersState(action.hamburgers, state.ingredients)
                is HamburgersAction.IngredientsFetched -> HamburgersState(state.hamburgers, action.ingredients)
                is HamburgersAction.FetchHamburgers -> { fetchHamburgers(); state }
                is HamburgersAction.FetchIngredients -> { fetchIngredients(); state }
                else -> state
            }
        }
    }
}