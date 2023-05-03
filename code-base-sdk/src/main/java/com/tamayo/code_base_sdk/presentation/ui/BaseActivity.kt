package com.tamayo.code_base_sdk.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.tamayo.code_base_sdk.R
import com.tamayo.code_base_sdk.databinding.ActivityBaseBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * An activity class for the base activity of the application, extending the AppCompatActivity class.
 */
@AndroidEntryPoint
class BaseActivity : AppCompatActivity() {

    /**
     * Lazily initializes the binding for the base activity.
     */
    private val binding: ActivityBaseBinding by lazy {
        ActivityBaseBinding.inflate(layoutInflater)
    }

    /**
     * Called when the activity is created, initializes the UI and sets up the action bar with the navigation controller.
     * @param savedInstanceState The saved instance state of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Find the NavHostFragment and set up the action bar with the navigation controller
        val navHost = supportFragmentManager.findFragmentById(R.id.base_container) as NavHostFragment
        setupActionBarWithNavController(navHost.navController)

    }

    /**
     * Called when the user selects the "up" button in the action bar, navigates up or delegates to the superclass.
     * @return true if navigation was successful, false otherwise.
     */
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.base_container)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}