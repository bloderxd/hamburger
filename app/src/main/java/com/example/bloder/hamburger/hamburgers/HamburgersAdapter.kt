package com.example.bloder.hamburger.hamburgers

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bloder.hamburger.api.models.Hamburger

/**
 * Created by bloder on 28/10/17.
 */
class HamburgersAdapter(private val context: Context, private val hamburgers: List<Hamburger>) : RecyclerView.Adapter<HamburgersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder = ViewHolder(context)
    override fun getItemCount(): Int = hamburgers.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(hamburgers[position])
    }

    class ViewHolder(context: Context) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(0, null)) {

        fun bind(hamburger: Hamburger) {

        }
    }
}