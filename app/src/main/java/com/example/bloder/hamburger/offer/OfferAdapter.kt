package com.example.bloder.hamburger.offer

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bloder.hamburger.R
import com.example.bloder.hamburger.api.models.Offer
import kotlinx.android.synthetic.main.offer_view_holder.view.*

/**
 * Created by bloder on 02/11/17.
 */
class OfferAdapter(private val context: Context, private val offers: List<Offer>) : RecyclerView.Adapter<OfferAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) { holder?.bind(offers[position]) }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder = ViewHolder(context)
    override fun getItemCount(): Int = offers.size

    class ViewHolder(context: Context) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.offer_view_holder, null)) {

        fun bind(offer: Offer) {
            itemView.title.text = offer.name
            itemView.description.text = offer.description
        }
    }
}