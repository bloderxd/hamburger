package com.example.bloder.hamburger.redux

import android.content.Intent
import java.io.Serializable

/**
 * Created by bloder on 30/10/17.
 */
class ReactViewIntentDSL(private val intent: Intent) {

    fun withExtra(name: String, serializable: Serializable) : ReactViewIntentDSL {
        intent.putExtra(name, serializable)
        return this
    }

    fun withExtra(name: String, serializableList: ArrayList<Serializable>?) : ReactViewIntentDSL {
        if (serializableList != null && serializableList.isNotEmpty()) intent.putExtra(name, serializableList)
        return this
    }

    fun intent() : Intent = intent
}