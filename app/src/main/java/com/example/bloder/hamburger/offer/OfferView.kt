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
import trikita.anvil.DSL.withId
import trikita.anvil.DSL.xml

/**
 * Created by bloder on 02/11/17.
 */
class OfferView(private val activity: Context) : ReactView<OfferState>(activity) {

    private val repository by lazy { HamburgerRepository.get(REPOSITORY_ENVIRONMENT.PROD) }
    private var error = false

    override fun onCreate(state: OfferState) {
        dispatch(OfferActions.FetchOffers({ fetchOffers() }))
    }

    override fun render(state: OfferState) {
        xml(R.layout.activity_offer) {

            withId(R.id.progress) {
                visibility = if (state.offers.isEmpty()) View.VISIBLE else View.GONE
            }

            withId(R.id.offers) {
                val view = Anvil.currentView<RecyclerView>()
                view.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                view.adapter = OfferAdapter(activity, state.offers)
            }

            withId(R.id.error_message) {
                visibility = if (state.offers.isEmpty() && error) View.VISIBLE else View.GONE
            }
        }
    }

    private fun fetchOffers() = repository.getOffers().subscribe { offers, error ->
        this@OfferView.error = error != null
        dispatch(OfferActions.OnOffersFetched(offers))
    }

    override fun buildStore(): Store<OfferState> = reduksStore {}
}