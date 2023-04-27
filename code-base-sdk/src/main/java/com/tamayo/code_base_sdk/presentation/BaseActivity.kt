package com.tamayo.code_base_sdk.presentation

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.tamayo.code_base_sdk.R
import com.tamayo.code_base_sdk.databinding.ActivityBaseBinding
import com.tamayo.code_base_sdk.databinding.FragmentDetailsBinding
import com.tamayo.code_base_sdk.utils.CharactersType
import com.tamayo.code_base_sdk.viewmodel.MainBaseViewModel

class BaseActivity : AppCompatActivity() {

    private val vm by lazy {
        ViewModelProvider(this)[MainBaseViewModel::class.java]
    }

    private val binding: ActivityBaseBinding by lazy {
        ActivityBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(
            R.id.base_container) as NavHostFragment
        setupActionBarWithNavController(navHost.navController)




    }

    //Navigate between fragment with the arrow
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.base_container)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}