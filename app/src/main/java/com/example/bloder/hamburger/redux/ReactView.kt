package com.example.bloder.hamburger.redux

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.reduks.reduks.Action
import com.reduks.reduks.Store
import com.reduks.reduks.subscription.Subscriber
import com.reduks.reduks.subscription.Subscription
import trikita.anvil.Anvil
import trikita.anvil.RenderableView
import kotlin.reflect.KProperty

/**
 * Created by bloder on 25/10/17.
 */
abstract class ReactView<State>(private val environment: Context) : RenderableView(environment), Subscriber<State> {

    private var store by lazy { buildStore() }
    private var subscription = Subscription {}
    private var activity : AppCompatActivity? = null

    abstract fun render(state: State)
    abstract fun buildStore() : Store<State>

    protected fun dispatch(action: Action<State>) {
        store.dispatch(action)
    }

    override fun stateChanged(state: State) {
        Anvil.render()
    }

    override fun onDetachedFromWindow() {
        Anvil.unmount(this, false)
        super.onDetachedFromWindow()
    }

    fun onCreateView() : View? {
        subscription = store.subscribe(this@ReactView)
        activity = environment as? AppCompatActivity
        return this
    }

    protected fun <T : AppCompatActivity> startActivity(activity: Class<T>, init: ReactViewIntentDSL.() -> ReactViewIntentDSL = { this }) {
        val dsl = ReactViewIntentDSL(Intent(environment, activity)).init()
        environment.startActivity(dsl.intent())
    }

    protected fun extras() : Bundle? = activity?.intent?.extras
    protected fun fragmentManager() : FragmentManager? = activity?.supportFragmentManager
    protected fun finish() = activity?.finish()
    fun onResume() = onResume(store.getState())
    fun onDestroy() = onDestroy(store.getState())
    fun onOptionsItemSelected(item: MenuItem?) = onOptionsItemSelected(store.getState(), item)
    fun onCreate() { onCreate(store.getState()) }
    open fun onResume(state: State) {}
    open fun onDestroy(state: State) = subscription.unsubscribe()
    open fun onOptionsItemSelected(state: State, item: MenuItem?) {}
    open fun menuToInflate() : Int = -1
    open fun onCreate(state: State) {}

    override fun view() = render(store.getState())

    private operator fun <T, State> Lazy<T>.setValue(reactView: ReactView<State>, property: KProperty<*>, t: T) {}
}