package com.example.bloder.hamburger.hamburger_details

import android.app.Activity
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.example.bloder.hamburger.R
import com.example.bloder.hamburger.api.models.Hamburger
import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.hamburger_details.offers.getTotalPrice
import com.example.bloder.hamburger.hamburger_details.redux.HamburgerDetailsAction
import com.example.bloder.hamburger.hamburger_details.redux.HamburgerDetailsState
import com.example.bloder.hamburger.redux.ReactView
import com.reduks.reduks.Store
import com.reduks.reduks.reduksStore
import com.squareup.picasso.Picasso
import trikita.anvil.Anvil
import trikita.anvil.DSL.*
import java.text.DecimalFormat

/**
 * Created by bloder on 30/10/17.
 */
class HamburgerDetailsView(private val activity: Context) : ReactView<HamburgerDetailsState>(activity) {

    private val ingredientsInfo by lazy { extras()?.getSerializable("ingredients") as List<Ingredient> }

    override fun onCreate(state: HamburgerDetailsState) {
        dispatch(HamburgerDetailsAction.UpdateDetail((extras()?.getSerializable("detail") as? Hamburger) ?: state.hamburger))
    }

    override fun render(state: HamburgerDetailsState) {
        xml(R.layout.activity_hamburger_details) {
            val adapter =
                    if ((withId(R.id.ingredients){} as RecyclerView).adapter == null) IngredientsDetailsAdapter(activity, convertHamburgerIngredients(state.hamburger.ingredients))
                    else (withId(R.id.ingredients){} as RecyclerView).adapter as IngredientsDetailsAdapter
            withId(R.id.image) { Picasso.with(activity).load(state.hamburger.image).into(Anvil.currentView<ImageView>()) }
            withId(R.id.name) { text(state.hamburger.name) }
            withId(R.id.ingredients) {
                val view = Anvil.currentView<RecyclerView>()
                view.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                view.adapter = adapter
            }
            withId(R.id.total) { text(buildPrice(adapter.getAllIngredients().getTotalPrice())) }
        }
    }

    private fun convertHamburgerIngredients(ids: List<Int>) : List<Ingredient> {
        val ingredients = mutableListOf<Ingredient>()
        ingredientsInfo.forEach { ingredient ->
            ids.filter { ingredient.id == it }.forEach { ingredients.add(ingredient) }
        }
        return ingredients
    }

    private fun getHamburgerTotal(hamburgerIngredients: List<Int>, ingredients: List<Ingredient>) : Double {
        var total = 0.0
        ingredients.forEach { (id, _, price) ->
            hamburgerIngredients.filter { id == it }
                    .forEach { total += price }
        }
        return total
    }

    private fun buildPrice(price: Double) = "R$${DecimalFormat("0.00").format(price)}"

    override fun buildStore(): Store<HamburgerDetailsState> = reduksStore {}
}