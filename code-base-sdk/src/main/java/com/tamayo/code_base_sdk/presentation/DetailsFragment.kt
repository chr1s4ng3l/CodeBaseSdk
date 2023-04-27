package com.tamayo.code_base_sdk.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tamayo.code_base_sdk.R
import com.tamayo.code_base_sdk.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {


    private val binding: FragmentDetailsBinding by lazy{
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {






        // Inflate the layout for this fragment
        return binding.root
    }

}