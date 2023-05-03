package com.tamayo.code_base_sdk.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.annotation.VisibleForTesting
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.tamayo.code_base_sdk.databinding.FragmentItemsBinding
import com.tamayo.code_base_sdk.presentation.adapter.AppAdapter
import com.tamayo.code_base_sdk.presentation.viewmodel.MainBaseViewModel
import com.tamayo.code_base_sdk.utils.DetailOnBackPressed
import com.tamayo.code_base_sdk.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Fragment for displaying items.
 */
@AndroidEntryPoint
class ItemsFragment : Fragment() {

    // Lazily initialize view binding
    private val binding: FragmentItemsBinding by lazy {
        FragmentItemsBinding.inflate(layoutInflater)
    }

    // Lazily initialize appAdapter
    @VisibleForTesting
    val appAdapter by lazy {
        AppAdapter {
            vm.getItemSelected(it)
            binding.slideItems.openPane()
        }
    }

    // Lazily initialize view model
    private val vm by lazy {
        ViewModelProvider(requireActivity())[MainBaseViewModel::class.java]
    }

    // Declare appType variable
    private lateinit var appType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get character type from activity intent
        appType = requireActivity().intent.getStringExtra("CHARACTER_TYPE") as String
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Set up search view listener
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

        // Set up back press callback
        onBackPressedCallBack()

        // Set up recycler view
        binding.mainFragment.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = appAdapter
        }

        // Observe loading state
        isLoading()

        // Fetch characters based on appType
        vm.getCharacters(appType)

        // Observe character type state
        getCharacterType()

        // Inflate the layout for this fragment
        return binding.root
    }

    /**
     * Function to search for a character.
     */
    private fun searchCharacter() {
        lifecycleScope.launch {
            vm.textQuery.collect { query ->
                appAdapter.performSearch(query)
            }
        }
    }

    /**
     * Function to get the character type.
     */
    private fun getCharacterType() {
        lifecycleScope.launch {
            vm.characterType.collect { state ->
                when (state) {
                    is UIState.ERROR -> { /* handle error state */ }
                    is UIState.LOADING -> { /* handle loading state */ }
                    is UIState.SUCCESS -> {
                        appAdapter.updateItems(state.data)
                    }
                }
            }
        }
    }

    /**
     * Function to observe the loading state.
     */
    private fun isLoading() {
        lifecycleScope.launch {
            vm.isLoading.collect {
                binding.mainFragment.progressBar.isGone = it
            }
        }
    }

    /**
     * Function to handle back press.
     */
    private fun onBackPressedCallBack() {
        binding.slideItems.lockMode = SlidingPaneLayout.LOCK_MODE_LOCKED
        DetailOnBackPressed(binding.slideItems).let {
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                it
            )
        }
    }
}
