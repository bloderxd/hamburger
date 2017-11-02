package com.example.bloder.hamburger.hamburger_details.ingredients_selector

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bloder.hamburger.R
import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.hamburger_details.IngredientsDetailsAdapter
import kotlinx.android.synthetic.main.ingredients_selector_view_holder.view.*

/**
 * Created by bloder on 02/11/17.
 */
class IngredientsSelectorAdapter(private val context: Context,
                                 private var ingredientsGrouped: MutableList<IngredientsDetailsAdapter.IngredientGroup>,
                                 private val allIngredients: List<Ingredient>) : RecyclerView.Adapter<IngredientsSelectorAdapter.ViewHolder>() {

    fun updateIngredientsGrouped(groups: MutableList<IngredientsDetailsAdapter.IngredientGroup>) {
        ingredientsGrouped = groups
        notifyDataSetChanged()
    }

    fun getAllIngredients() : List<Ingredient> {
        val ingredients = mutableListOf<Ingredient>()
        ingredientsGrouped.forEach { ingredients.addAll(it.ingredients) }
        return ingredients
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) { holder?.bind(allIngredients[position], ingredientsGrouped, { groups -> updateIngredientsGrouped(groups) }) }
    override fun getItemCount(): Int = allIngredients.size
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder = ViewHolder(context)

    class ViewHolder(context: Context) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.ingredients_selector_view_holder, null)) {

        fun bind(ingredient: Ingredient, ingredientsGrouped: MutableList<IngredientsDetailsAdapter.IngredientGroup>, updateGroup: (MutableList<IngredientsDetailsAdapter.IngredientGroup>) -> Any?) {
            itemView.name.text = ingredient.name
            itemView.ingredient_selected.text = ingredientCount(ingredient, ingredientsGrouped).toString()
            itemView.ingredient_increase.setOnClickListener { updateGroup(buildGroupIncreasing(ingredient, ingredientsGrouped)) }
            itemView.ingredient_decrease.setOnClickListener { updateGroup(buildGroupDecreasing(ingredient, ingredientsGrouped)) }
        }

        private fun buildGroupIncreasing(ingredient: Ingredient, ingredientsGrouped: MutableList<IngredientsDetailsAdapter.IngredientGroup>) : MutableList<IngredientsDetailsAdapter.IngredientGroup> {
            val group = ingredientsGrouped.firstOrNull { it.id == ingredient.id }
            if (group == null) {
                ingredientsGrouped.add(IngredientsDetailsAdapter.IngredientGroup(ingredient.id, mutableListOf(ingredient)))
                return ingredientsGrouped
            }
            val index = ingredientsGrouped.indexOf(group)
            ingredientsGrouped.remove(group)
            group.ingredients.add(group.ingredients[0])
            ingredientsGrouped.add(index, group)
            return ingredientsGrouped
        }

        private fun buildGroupDecreasing(ingredient: Ingredient, ingredientsGrouped: MutableList<IngredientsDetailsAdapter.IngredientGroup>) : MutableList<IngredientsDetailsAdapter.IngredientGroup> {
            val group = ingredientsGrouped.firstOrNull { it.id == ingredient.id } ?: return ingredientsGrouped
            if (group.ingredients.size == 1) {
                ingredientsGrouped.remove(group)
                return ingredientsGrouped
            } else {
                val index = ingredientsGrouped.indexOf(group)
                ingredientsGrouped.remove(group)
                group.ingredients.removeAt(0)
                ingredientsGrouped.add(index, group)
                return ingredientsGrouped
            }
        }

        private fun ingredientCount(ingredient: Ingredient, ingredientsGrouped: List<IngredientsDetailsAdapter.IngredientGroup>) : Int =
                ingredientsGrouped.firstOrNull { it.id == ingredient.id }?.ingredients?.size ?: 0
    }
}