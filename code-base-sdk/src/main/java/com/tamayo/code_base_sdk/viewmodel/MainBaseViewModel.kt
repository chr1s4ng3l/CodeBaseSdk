package com.tamayo.code_base_sdk.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamayo.code_base_sdk.usecases.CharacterUseCaseClass
import com.tamayo.code_base_sdk.utils.CharactersType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainBaseViewModel @Inject constructor(
    private val characterUseCaseClass: CharacterUseCaseClass
) : ViewModel() {

    var appType: CharactersType? = null

    fun getCharacters() {
        appType?.let { characterType ->
            viewModelScope.launch {
                characterUseCaseClass.invoke(characterType).collect {
                }
            }
        }
    }
}
