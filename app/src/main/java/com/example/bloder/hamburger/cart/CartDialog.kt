package com.example.bloder.hamburger.cart

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.bloder.hamburger.R
import com.example.bloder.hamburger.api.models.Cart
import com.example.bloder.hamburger.api.models.Hamburger
import com.example.bloder.hamburger.hamburger_details.bottom_sheet.FullScreenBottomSheet
import com.example.bloder.hamburger.repository.HamburgerRepository
import com.example.bloder.hamburger.repository.REPOSITORY_ENVIRONMENT
import kotlinx.android.synthetic.main.cart_dialog.view.*

/**
 * Created by bloder on 02/11/17.
 */
class CartDialog(private val hamburgers: List<Hamburger>) : FullScreenBottomSheet() {

    constructor() : this(listOf())

    private val repository by lazy { HamburgerRepository.get(REPOSITORY_ENVIRONMENT.PROD) }

    override fun onViewInflated(view: View) {
        view.progress.visibility = View.VISIBLE
        fetchCart(view)
    }

    private fun fetchCart(view: View) = repository.getCart().subscribe { cart, error ->
        if (cart == null && error != null) treatError(view, "Ocorreu um problema com a conexão com o servidor")
        else if (cart.isEmpty()) treatError(view, "Você ainda não tem nenhum pedido.")
        else successFetched(view, cart)
    }

    private fun treatError(view: View, error: String) {
        view.progress.visibility = View.GONE
        view.cart.visibility = View.GONE
        view.error_message.text = error
        view.error_message.visibility = View.VISIBLE
    }

    private fun successFetched(view: View, cart: List<Cart>) {
        view.progress.visibility = View.GONE
        view.cart.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        view.cart.adapter = CartAdapter(context, cart, hamburgers)
    }

    override fun viewToInflate(): Int = R.layout.cart_dialog
}