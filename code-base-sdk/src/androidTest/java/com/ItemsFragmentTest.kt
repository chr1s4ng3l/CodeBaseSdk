package com

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import com.tamayo.code_base_sdk.presentation.ui.ItemsFragment
import junit.framework.TestCase.assertTrue
import org.junit.Test

class ItemsFragmentTest {


    @Test
    fun first_fragment_test() {

        val fragment = ItemsFragment()
        val scenario = launchFragmentInContainer<ItemsFragment>{
            fragment
        }

        scenario.moveToState(Lifecycle.State.RESUMED)

        assertTrue(fragment.isResumed)
    }

}