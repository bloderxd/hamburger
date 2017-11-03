package com.example.bloder.hamburger.hamburger_details

import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.example.bloder.hamburger.R
import com.example.bloder.hamburger.api.models.Hamburger
import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.hamburger_details.ingredients_selector.SelectIngredentsDialog
import com.example.bloder.hamburger.hamburger_details.offers.getTotalPrice
import com.example.bloder.hamburger.hamburger_details.redux.HamburgerDetailsAction
import com.example.bloder.hamburger.hamburger_details.redux.HamburgerDetailsState
import com.example.bloder.hamburger.redux.ReactView
import com.example.bloder.hamburger.repository.HamburgerRepository
import com.example.bloder.hamburger.repository.REPOSITORY_ENVIRONMENT
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
    private val repository      by lazy { HamburgerRepository.get(REPOSITORY_ENVIRONMENT.PROD) }
    private var firstIngredientLists: List<Ingredient> = listOf()

    override fun onCreate(state: HamburgerDetailsState) {
        dispatch(HamburgerDetailsAction.UpdateDetail((extras()?.getSerializable("detail") as? Hamburger) ?: state.hamburger))
    }

    override fun render(state: HamburgerDetailsState) {
        xml(R.layout.activity_hamburger_details) {

            val adapter =
                    if ((withId(R.id.ingredients){} as RecyclerView).adapter == null) IngredientsDetailsAdapter(activity, convertHamburgerIngredients(state), { adapter -> openToCustomIngredients(adapter) })
                    else (withId(R.id.ingredients){} as RecyclerView).adapter as IngredientsDetailsAdapter

            if (state.ingredients.isEmpty()) firstIngredientLists = adapter.getAllIngredients()

            withId(R.id.image) { Picasso.with(activity).load(state.hamburger.image).into(Anvil.currentView<ImageView>()) }

            withId(R.id.name) {
                text(
                        if (state.ingredients.isEmpty() || arrayOf(state.ingredients) contentEquals arrayOf(firstIngredientLists)) state.hamburger.name
                        else "${state.hamburger.name} - do seu jeito"
                )
            }

            withId(R.id.ingredients) {
                val view = Anvil.currentView<RecyclerView>()
                view.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                view.adapter = if (view.adapter == null) adapter else view.adapter
            }

            withId(R.id.total) { text(buildPrice(
                    if (state.ingredients.isEmpty()) adapter.getAllIngredients().getTotalPrice()
                    else state.ingredients.getTotalPrice()
            ))}

            withId(R.id.confirm) {
                onClick { book(state) }
            }
        }
    }

    private fun book(state: HamburgerDetailsState) {
        if (state.ingredients.isEmpty() || arrayOf(state.ingredients) contentEquals arrayOf(firstIngredientLists)) normalBook(state)
        else bookWithExtraIngredients(state)
    }

    private fun normalBook(state: HamburgerDetailsState) = repository.addToCart(state.hamburger.id).subscribe { _, error ->
        treatResponse(error)
    }

    private fun bookWithExtraIngredients(state: HamburgerDetailsState) = repository.addToCart(state.hamburger.id, getExtrasIngredients(state.ingredients)).subscribe { _, error ->
        treatResponse(error)
    }

    private fun treatResponse(error: Throwable?) {
        when (error) {
            null -> {
                AlertDialog.Builder(activity)
                        .setTitle("Sucesso!")
                        .setMessage("O pedido foi inserido no carrinho.")
                        .setPositiveButton("Ok", { dialog, _ -> dialog.dismiss(); finish() })
                        .show()
            }
            else -> AlertDialog.Builder(activity)
                    .setTitle("Ops!")
                    .setMessage("Ocorreu um problema ao inserir sua compra ao carrinho.")
                    .setPositiveButton("Ok", { dialog, _ -> dialog.dismiss() })
                    .show()
        }
    }

    private fun getExtrasIngredients(ingredients: List<Ingredient>) : List<Int> {
        val list = mutableListOf<Int>()
        ingredients.forEach { (id) ->
            var countId = 0
            ingredients.forEach {
                if (id == it.id) countId++
            }
            if (countId > 1) list.add(id)
        }
        return list
    }

    private fun convertHamburgerIngredients(state: HamburgerDetailsState) : List<Ingredient> {
        if (state.ingredients.isEmpty()) {
            val ingredients = mutableListOf<Ingredient>()
            ingredientsInfo.forEach { ingredient ->
                state.hamburger.ingredients.filter { ingredient.id == it }.forEach { ingredients.add(ingredient) }
            }
            return ingredients
        } else {
            return state.ingredients
        }
    }

    private fun openToCustomIngredients(adapter: IngredientsDetailsAdapter) = SelectIngredentsDialog(adapter.getAllGroupedIngredients(), ingredientsInfo, this).show(fragmentManager(), "")
    private fun buildPrice(price: Double) = "R$${DecimalFormat("0.00").format(price)}"
    fun updateIngredients(ingredients: List<Ingredient>) = dispatch(HamburgerDetailsAction.UpdateIngredients(ingredients))
    override fun buildStore(): Store<HamburgerDetailsState> = reduksStore {}
}