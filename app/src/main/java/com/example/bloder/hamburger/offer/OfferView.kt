package com.example.bloder.hamburger.offer

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.bloder.hamburger.R
import com.example.bloder.hamburger.offer.redux.OfferActions
import com.example.bloder.hamburger.offer.redux.OfferState
import com.example.bloder.hamburger.redux.ReactView
import com.example.bloder.hamburger.repository.HamburgerRepository
import com.example.bloder.hamburger.repository.REPOSITORY_ENVIRONMENT
import com.reduks.reduks.Store
import com.reduks.reduks.reduksStore
import trikita.anvil.Anvil
import trikita.anvil.DSL.*

/**
 * Created by bloder on 02/11/17.
 */
class OfferView(private val activity: Context) : ReactView<OfferState>(activity) {

    private val repository by lazy { HamburgerRepository.get(REPOSITORY_ENVIRONMENT.PROD) }
    private var error = false

    override fun render(state: OfferState) {
        if (state.offers.isEmpty()) fetchOffers()
        if (state.offers.isNotEmpty()) {
            xml(R.layout.activity_offer) {

                withId(R.id.progress_offer) {
                    visibility(state.offers.isEmpty())
                }

                withId(R.id.offers) {
                    visibility(state.offers.isNotEmpty())
                    val view = Anvil.currentView<RecyclerView>()
                    view.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    view.adapter = OfferAdapter(activity, state.offers)
                }
            }
        }
    }

    private fun fetchOffers() = repository.getOffers().subscribe { offers, error ->
        this@OfferView.error = error != null
        dispatch(OfferActions.OnOffersFetched(offers))
    }

    override fun buildStore(): Store<OfferState> = reduksStore {}
}