package com.example.bloder.hamburger.hamburger_details.ingredients_selector

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.bloder.hamburger.R
import com.example.bloder.hamburger.api.models.Ingredient
import com.example.bloder.hamburger.hamburger_details.HamburgerDetailsView
import com.example.bloder.hamburger.hamburger_details.IngredientsDetailsAdapter
import com.example.bloder.hamburger.hamburger_details.bottom_sheet.FullScreenBottomSheet
import kotlinx.android.synthetic.main.ingredients_selector.view.*


/**
 * Created by bloder on 02/11/17.
 */
class SelectIngredentsDialog(private val ingredentsGrouped: MutableList<IngredientsDetailsAdapter.IngredientGroup>,
                             private val allIngredients: List<Ingredient>,
                             private val reactView: HamburgerDetailsView?) : FullScreenBottomSheet() {

    constructor() : this(mutableListOf(), listOf(), null)

    private val adapter by lazy { IngredientsSelectorAdapter(
            activity,
            ingredentsGrouped,
            allIngredients
    )}

    override fun onViewInflated(view: View) {
        view.ingredients.layoutManager = LinearLayoutManager(context)
        view.ingredients.adapter = adapter
        view.confirm.setOnClickListener { dismiss() }
    }

    override fun viewToInflate(): Int = R.layout.ingredients_selector

    override fun onDestroy() {
        super.onDestroy()
        reactView?.updateIngredients(adapter.getAllIngredients())
    }
}