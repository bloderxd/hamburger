package com.example.bloder.hamburger.redux

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

/**
 * Created by bloder on 25/10/17.
 */
abstract class ReactActivity(private val viewClass: Class<*>) : AppCompatActivity() {

    private var view: ReactView<State>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = try { viewClass.getConstructor(Context::class.java).newInstance(this) as ReactView<State> } catch (e: Exception) { throw Exception("View class need to be a ReactView extension") }
        setContentView(view?.onCreateView())
        view?.onCreate()
    }

    override fun onResume() {
        super.onResume()
        view?.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        view?.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        view?.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (view?.menuToInflate() != null && view?.menuToInflate()!! > -1) menuInflater.inflate(view?.menuToInflate()?:-1, menu)
        return super.onCreateOptionsMenu(menu)
    }
}