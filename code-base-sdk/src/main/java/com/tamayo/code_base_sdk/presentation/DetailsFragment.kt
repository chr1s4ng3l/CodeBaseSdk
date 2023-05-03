package com.tamayo.code_base_sdk.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.load
import com.tamayo.code_base_sdk.R
import com.tamayo.code_base_sdk.databinding.FragmentDetailsBinding
import com.tamayo.code_base_sdk.presentation.viewmodel.MainBaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    // Use lazy initialization to inflate the binding only when needed
    private val binding: FragmentDetailsBinding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    // Use lazy initialization to get the ViewModel only when needed
    private val vm: MainBaseViewModel by lazy {
        ViewModelProvider(requireActivity())[MainBaseViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Call the function to get the details
        getDetails()

        // Return the inflated view from the binding
        return binding.root
    }

    private fun getDetails() {
        // Observe the selected item LiveData from the ViewModel
        lifecycleScope.launch {
            vm.itemSelected.collect { item ->

                // Check if the item has a valid icon URL, otherwise use a default icon
                val icon = when {
                    item?.icon?.contains("/i/") == true -> item.icon
                    else -> R.drawable.nofoundcharacter
                }

                // Load the icon into the ImageView using Coil library
                binding.imageDetail.load(icon) {
                    crossfade(true).build()
                }

                // Set the name and description TextViews from the selected item
                binding.tvName.text = item?.name ?: "Name will be displayed here"
                binding.tvDescription.text =
                    item?.description ?: "Description about the character will be displayed here"
            }
        }
    }
}
