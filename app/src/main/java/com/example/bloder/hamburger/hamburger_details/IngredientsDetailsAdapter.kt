package com.example.bloder.hamburger.hamburger_details

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.bloder.hamburger.R
import com.example.bloder.hamburger.api.models.Ingredient
import java.text.DecimalFormat

/**
 * Created by bloder on 31/10/17.
 */
class IngredientsDetailsAdapter(private val context: Context, private val hamburgerIngredients: List<Ingredient>) : RecyclerView.Adapter<IngredientsDetailsAdapter.ViewHolder>() {

    private var ingredientGroups: MutableList<IngredientGroup> = mutableListOf()

    init { ingredientGroups = buildIngredientGroups() }

    fun getAllIngredients() : List<Ingredient> {
        val ingredients = mutableListOf<Ingredient>()
        ingredientGroups.forEach { ingredients.addAll(it.ingredients) }
        return ingredients
    }

    private fun buildIngredientGroups() : MutableList<IngredientGroup> {
        val groups = mutableListOf<IngredientGroup>()
        hamburgerIngredients.forEach { hamburgerIngredient ->
            if (groups.isNotEmpty() && groups.firstOrNull { it.id == hamburgerIngredient.id } != null) {
                groups.firstOrNull { it.id == hamburgerIngredient.id }?.ingredients?.add(hamburgerIngredient)
            } else {
                groups.add(IngredientGroup(hamburgerIngredient.id, mutableListOf(hamburgerIngredient)))
            }
        }
        return groups
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(ingredientGroups[position])
    }

    override fun getItemCount(): Int = ingredientGroups.size
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder = ViewHolder(context)

    class ViewHolder(context: Context) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.ingredient_details_view_holder, null)) {

        fun bind(ingredient: IngredientGroup) {
            itemView.findViewById<TextView>(R.id.name).text = "${ingredient.ingredients[0].name} x${ingredient.ingredients.size}"
            itemView.findViewById<TextView>(R.id.price).text = buildPrice(getTotalIngredient(ingredient.ingredients))
        }

        private fun getTotalIngredient(ingredients: MutableList<Ingredient>) : Double {
            var total = 0.0
            ingredients.forEach { total += it.price }
            return total
        }

        private fun buildPrice(price: Double) = "R$${DecimalFormat("0.00").format(price)}"
    }

    data class IngredientGroup(val id: Int, val ingredients: MutableList<Ingredient> = mutableListOf())
}