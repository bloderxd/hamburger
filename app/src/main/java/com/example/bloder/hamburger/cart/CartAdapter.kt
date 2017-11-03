package com.example.bloder.hamburger.cart

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bloder.hamburger.R
import com.example.bloder.hamburger.api.models.Cart
import com.example.bloder.hamburger.api.models.Hamburger
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_view_holder.view.*

/**
 * Created by bloder on 02/11/17.
 */
class CartAdapter(private val context: Context, private val cart: List<Cart>, private val hamburgers: List<Hamburger>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun getItemCount(): Int = cart.size
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) { holder?.bind(cart[position], hamburgers) }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder = ViewHolder(context)

    class ViewHolder(private val context: Context) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_view_holder, null)) {

        fun bind(cart: Cart, hamburgers: List<Hamburger>) {
            val hamburger = hamburgerFromId(cart.hamburgerId, hamburgers)
            Picasso.with(context).load(hamburger.image).into(itemView.image)
            itemView.name.text = hamburger.name
        }

        private fun hamburgerFromId(id: Int, hamburgers: List<Hamburger>) : Hamburger = hamburgers.first {
            it.id == id
        }
    }
}