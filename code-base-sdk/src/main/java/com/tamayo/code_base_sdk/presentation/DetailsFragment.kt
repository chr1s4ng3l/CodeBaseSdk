package com.tamayo.code_base_sdk.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.load
import com.tamayo.code_base_sdk.R
import com.tamayo.code_base_sdk.databinding.FragmentDetailsBinding
import com.tamayo.code_base_sdk.presentation.viewmodel.MainBaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint

class DetailsFragment : Fragment() {


    private val binding: FragmentDetailsBinding by lazy{
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    private val vm: MainBaseViewModel by lazy{
        ViewModelProvider(requireActivity())[MainBaseViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        getDetails()

        // Inflate the layout for this fragment
        return binding.root
    }


    private fun getDetails() {
        lifecycleScope.launch{
            vm.itemSelected.collect{

                val icon = when {
                    it?.icon?.contains("/i/") == true -> it.icon
                    else -> R.drawable.nofoundcharacter
                }

                binding.imageDetail.load(icon){
                    crossfade(true).build()
                }
                binding.tvName.text = it?.name ?: "Name will be display here"
                binding.tvDescription.text = it?.description ?: "Description about the character will be display here"

            }

        }
    }

}