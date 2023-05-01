package com.tamayo.code_base_sdk.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamayo.code_base_sdk.domain.DomainCharacter
import com.tamayo.code_base_sdk.domain.usecases.CharacterUseCaseClass
import com.tamayo.code_base_sdk.utils.CharactersType
import com.tamayo.code_base_sdk.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Thread.State
import javax.inject.Inject

@HiltViewModel
class MainBaseViewModel @Inject constructor(
    private val characterUseCaseClass: CharacterUseCaseClass
) : ViewModel() {


    private val _itemSelected: MutableStateFlow<DomainCharacter?> = MutableStateFlow(null)
    val itemSelected: StateFlow<DomainCharacter?> get() = _itemSelected

    private val _characterType: MutableStateFlow<UIState<List<DomainCharacter>>> = MutableStateFlow(UIState.LOADING)
    val characterType : StateFlow<UIState<List<DomainCharacter>>> get() = _characterType

    private val _textQuery: MutableStateFlow<String> = MutableStateFlow("")
    val textQuery : StateFlow<String> get() = _textQuery


    fun getItemSelected(item: DomainCharacter){
        _itemSelected.value = item
    }

    fun getCharacters(characterType: String) {
            viewModelScope.launch {
                characterUseCaseClass.invoke(characterType).collect {
                    _characterType.value = it
                }
        }
    }


    fun searchItems(text: String?){
        text?.let {

            _textQuery.value = text

        }
    }
}
