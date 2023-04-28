package com.tamayo.code_base_sdk.presentation

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tamayo.code_base_sdk.databinding.FragmentItemsBinding
import com.tamayo.code_base_sdk.presentation.adapter.AppAdapter
import com.tamayo.code_base_sdk.utils.CharactersType
import com.tamayo.code_base_sdk.utils.UIState
import com.tamayo.code_base_sdk.presentation.viewmodel.MainBaseViewModel
import kotlinx.coroutines.launch


class ItemsFragment : Fragment() {

    private val binding: FragmentItemsBinding by lazy {
        FragmentItemsBinding.inflate(layoutInflater)
    }

    private val appAdapter by lazy {
        AppAdapter {
            vm.getItemSelected(it)
            binding.slideItems.openPane()

        }
    }

    private val vm by lazy {
        ViewModelProvider(requireActivity())[MainBaseViewModel::class.java]
    }

    private lateinit var appType: CharactersType
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireActivity().intent.getSerializableExtra(
                "CHARACTER_TYPE",
                CharactersType::class.java
            )!!
        } else {
            requireActivity().intent.getSerializableExtra("CHARACTER_TYPE") as CharactersType
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.mainFragment.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                TODO("Not yet implemented")
            }

        })

        binding.mainFragment.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = appAdapter
        }

        getCharacterType()

        vm.getCharacters(appType)


        // Inflate the layout for this fragment
        return binding.root
    }


    private fun getCharacterType() {
        lifecycleScope.launch {
            vm.characterType.collect { state ->
                when (state) {
                    is UIState.ERROR -> {}
                    is UIState.LOADING -> {
                        //TODO progressbar
                    }

                    is UIState.SUCCESS -> {
                        appAdapter.updateItems(state.data)
                    }
                }
            }
        }
    }

}