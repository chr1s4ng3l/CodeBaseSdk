package com.tamayo.code_base_sdk.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.tamayo.code_base_sdk.R
import com.tamayo.code_base_sdk.databinding.ActivityBaseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : AppCompatActivity() {

    private val binding: ActivityBaseBinding by lazy {
        ActivityBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val navHost = supportFragmentManager.findFragmentById(R.id.base_container) as NavHostFragment
        setupActionBarWithNavController(navHost.navController)

    }

    //Navigate between fragment with the arrow
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.base_container)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}