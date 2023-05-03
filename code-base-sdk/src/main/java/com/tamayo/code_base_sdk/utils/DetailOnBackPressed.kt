package com.tamayo.code_base_sdk.utils

import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.slidingpanelayout.widget.SlidingPaneLayout

/**
 * Custom [OnBackPressedCallback] that handles back button presses when using [SlidingPaneLayout]
 * and sets the slide listener accordingly.
 * @param slidingPaneLayout the [SlidingPaneLayout] that handles the sliding behavior.
 */
class DetailOnBackPressed(private val slidingPaneLayout: SlidingPaneLayout):
    OnBackPressedCallback(slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen), SlidingPaneLayout.PanelSlideListener {

    init {
        slidingPaneLayout.addPanelSlideListener(this)
    }

    /**
     * Called when the back button is pressed.
     */
    override fun handleOnBackPressed() {
        slidingPaneLayout.closePane()
    }

    /**
     * Called when the panel is being slid.
     * @param panel the panel being slid.
     * @param slideOffset the offset of the panel being slid.
     */
    override fun onPanelSlide(panel: View, slideOffset: Float) {
        // No implementation needed
    }

    /**
     * Called when the panel has been fully opened.
     * @param panel the panel that has been opened.
     */
    override fun onPanelOpened(panel: View) {
        isEnabled = true
    }

    /**
     * Called when the panel has been fully closed.
     * @param panel the panel that has been closed.
     */
    override fun onPanelClosed(panel: View) {
        isEnabled = false
    }
}