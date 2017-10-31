package com.example.bloder.hamburger.hamburgers

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.bloder.hamburger.R
import com.example.bloder.hamburger.api.models.Hamburger
import com.example.bloder.hamburger.api.models.Ingredient
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

/**
 * Created by bloder on 28/10/17.
 */
class HamburgersAdapter(private val context: Context, private val hamburgers: List<Hamburger>, private val ingredients: List<Ingredient>, private val clickAction: (Hamburger) -> Any?) : RecyclerView.Adapter<HamburgersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder = ViewHolder(context, clickAction)
    override fun getItemCount(): Int = hamburgers.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(hamburgers[position], ingredients)
    }

    class ViewHolder(private val context: Context, private val clickAction: (Hamburger) -> Any?) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.hamburger_view_holder, null)) {

        fun bind(hamburger: Hamburger, ingredients: List<Ingredient> = listOf()) {
            itemView.setOnClickListener { clickAction(hamburger) }
            itemView.findViewById<TextView>(R.id.name).text = hamburger.name
            itemView.findViewById<TextView>(R.id.ingredients).text = findAllIngredientsIn(hamburger.ingredients, ingredients)
            itemView.findViewById<TextView>(R.id.price).text = buildPrice(getHamburgerTotal(hamburger.ingredients, ingredients))
            Picasso.with(context).load(hamburger.image).into(itemView.findViewById<ImageView>(R.id.hamburger_image))
        }

        private fun findAllIngredientsIn(hamburgerIngredients: List<Int>, ingredients: List<Ingredient>) : String {
            var value = ""
            ingredients.forEach { (id, name) ->
                hamburgerIngredients.filter { id == it }
                        .forEach { value += if (value.isBlank()) name else ", $name" }
            }
            return value
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
    }
}