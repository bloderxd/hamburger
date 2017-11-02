package com.example.bloder.hamburger.hamburger_details

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.bloder.hamburger.R
import com.example.bloder.hamburger.api.models.Ingredient
import kotlinx.android.synthetic.main.ingredient_details_custom_view_holder.view.*
import java.text.DecimalFormat

/**
 * Created by bloder on 31/10/17.
 */
class IngredientsDetailsAdapter(private val context: Context, private val hamburgerIngredients: List<Ingredient>, private val custom: (IngredientsDetailsAdapter) -> Any?) : RecyclerView.Adapter<IngredientsDetailsAdapter.ViewHolder>() {

    private var ingredientGroups: MutableList<IngredientGroup> = mutableListOf()

    init { ingredientGroups = buildIngredientGroups() }

    fun getAllIngredients() : List<Ingredient> {
        val ingredients = mutableListOf<Ingredient>()
        ingredientGroups.forEach { ingredients.addAll(it.ingredients) }
        return ingredients
    }

    fun getAllGroupedIngredients() : MutableList<IngredientGroup> = ingredientGroups

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
        holder?.bind(
                if (position == ingredientGroups.size) ingredientGroups[position -1]
                else ingredientGroups[position]
        )
    }

    override fun getItemCount(): Int = ingredientGroups.size + 1

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder = when(viewType) {
        ADAPTER_TYPE.INGREDIENT_DETAILS.id -> IngredientDetailsViewHolder(context)
        else -> CustomViewHolder(context, this, custom)
    }
    override fun getItemViewType(position: Int): Int = when(position) {
        ingredientGroups.lastIndex + 1 -> ADAPTER_TYPE.CUSTOM_BUTTON.id
        else -> ADAPTER_TYPE.INGREDIENT_DETAILS.id
    }

    abstract class ViewHolder(context: Context, layout: Int) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(layout, null)) {
        abstract fun bind(ingredient: IngredientGroup)
    }

    class IngredientDetailsViewHolder(context: Context) : ViewHolder(context, R.layout.ingredient_details_view_holder) {

        override fun bind(ingredient: IngredientGroup) {
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

    class CustomViewHolder(context: Context, private val adapter: IngredientsDetailsAdapter, private val custom: (IngredientsDetailsAdapter) -> Any?) : ViewHolder(context, R.layout.ingredient_details_custom_view_holder) {

        override fun bind(ingredient: IngredientGroup) {
            itemView.custom.setOnClickListener { custom(adapter) }
        }
    }

    enum class ADAPTER_TYPE(val id: Int) {
        INGREDIENT_DETAILS(0),
        CUSTOM_BUTTON(1)
    }
    data class IngredientGroup(val id: Int, val ingredients: MutableList<Ingredient> = mutableListOf())
}