package com.tamayo.code_base_sdk.presentation

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.tamayo.code_base_sdk.databinding.FragmentItemsBinding
import com.tamayo.code_base_sdk.presentation.adapter.AppAdapter
import com.tamayo.code_base_sdk.utils.CharactersType
import com.tamayo.code_base_sdk.utils.UIState
import com.tamayo.code_base_sdk.presentation.viewmodel.MainBaseViewModel
import com.tamayo.code_base_sdk.utils.DetailOnBackPressed
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ItemsFragment : Fragment() {

    private val binding: FragmentItemsBinding by lazy {
        FragmentItemsBinding.inflate(layoutInflater)
    }

    @VisibleForTesting
    val appAdapter by lazy {
        AppAdapter {
            vm.getItemSelected(it)
            binding.slideItems.openPane()

        }
    }

    private val vm by lazy {
        ViewModelProvider(requireActivity())[MainBaseViewModel::class.java]
    }

    private lateinit var appType: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appType = requireActivity().intent.getStringExtra("CHARACTER_TYPE") as String

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.mainFragment.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }


            override fun onQueryTextChange(newText: String): Boolean {

                vm.searchItems(newText)
                searchCharacter()

                return true
            }

        })

        onBackPressedCallBack()


        binding.mainFragment.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = appAdapter
        }


        vm.getCharacters(appType)

        getCharacterType()


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun searchCharacter() {
        lifecycleScope.launch {
            vm.textQuery.collect { query ->
                appAdapter.performSearch(query)
            }
        }

    }

    private fun getCharacterType() {
        lifecycleScope.launch {
            vm.characterType.collect { state ->
                when (state) {
                    is UIState.ERROR -> {
                        Log.d("TAG", "getCharacterType: Error")

                    }

                    is UIState.LOADING -> {
                        Log.d("TAG", "getCharacterType: Loading")
                    }

                    is UIState.SUCCESS -> {
                        appAdapter.updateItems(state.data)
                    }
                }
            }
        }
    }



    fun onBackPressedCallBack(){
       binding.slideItems.lockMode = SlidingPaneLayout.LOCK_MODE_LOCKED
        DetailOnBackPressed(binding.slideItems).let {
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                it
            )
        }

    }

}