package com.example.bloder.hamburger.hamburger_details.bottom_sheet

import android.app.Dialog
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.util.DisplayMetrics
import android.view.View

/**
 * Created by bloder on 02/11/17.
 */
abstract class FullScreenBottomSheet : BottomSheetDialogFragment() {

    private val bottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {}

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) dismiss()
        }
    }

    override fun setupDialog(dialog: Dialog?, style: Int) {
        super.setupDialog(dialog, style)
        val inflatedView = View.inflate(context, viewToInflate(), null)
        dialog?.setContentView(inflatedView)
        makeFullScreen(inflatedView)
        onViewInflated(inflatedView)
    }

    private fun makeFullScreen(inflatedView: View) {
        val params = (inflatedView.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior

        if (behavior != null && behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(bottomSheetBehaviorCallback)
        }

        val parent = inflatedView.parent as View
        parent.fitsSystemWindows = true
        val bottomSheetBehavior = BottomSheetBehavior.from(parent)
        inflatedView.measure(0, 0)
        val displaymetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displaymetrics)
        val screenHeight = displaymetrics.heightPixels
        bottomSheetBehavior.peekHeight = screenHeight

        if (params.behavior is BottomSheetBehavior<*>) {
            (params.behavior as BottomSheetBehavior<*>).setBottomSheetCallback(bottomSheetBehaviorCallback)
        }

        params.height = screenHeight
        parent.layoutParams = params
    }

    abstract fun viewToInflate() : Int
    abstract fun onViewInflated(view: View)
}