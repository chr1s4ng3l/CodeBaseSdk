package com.tamayo.code_base_sdk.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamayo.code_base_sdk.domain.DomainCharacter
import com.tamayo.code_base_sdk.domain.usecases.CharacterUseCaseClass
import com.tamayo.code_base_sdk.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The MainBaseViewModel class represents the ViewModel for the MainActivity screen. This ViewModel is responsible
 * for managing the data related to the list of characters, including retrieving the data from the UseCaseClass and
 * exposing the data to the View layer via different StateFlow variables.
 * @property characterUseCaseClass The UseCaseClass that retrieves the data related to characters
 * @property _itemSelected The private MutableStateFlow variable that represents the currently selected character
 * @property itemSelected The public StateFlow variable that exposes the currently selected character
 * @property _characterType The private MutableStateFlow variable that represents the list of characters
 * @property characterType The public StateFlow variable that exposes the list of characters
 * @property _textQuery The private MutableStateFlow variable that represents the current text query entered by the user
 * @property textQuery The public StateFlow variable that exposes the current text query
 * @property _isLoading The private MutableStateFlow variable that represents whether data is being loaded or not
 * @property isLoading The public StateFlow variable that exposes whether data is being loaded or not
 */
@HiltViewModel
class MainBaseViewModel @Inject constructor(
    private val characterUseCaseClass: CharacterUseCaseClass
) : ViewModel() {


    private val _itemSelected: MutableStateFlow<DomainCharacter?> = MutableStateFlow(null)
    val itemSelected: StateFlow<DomainCharacter?> get() = _itemSelected

    private val _characterType: MutableStateFlow<UIState<List<DomainCharacter>>> =
        MutableStateFlow(UIState.LOADING)
    val characterType: StateFlow<UIState<List<DomainCharacter>>> get() = _characterType

    private val _textQuery: MutableStateFlow<String> = MutableStateFlow("")
    val textQuery: StateFlow<String> get() = _textQuery

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading


    /**
     * This method is called when a character is selected from the list. It sets the [_itemSelected] value to the
     * selected character.
     * @param item The selected character.
     */
    fun getItemSelected(item: DomainCharacter) {
        _itemSelected.value = item
    }

    /**
     * This method retrieves the list of characters based on the given character type, and updates the [_characterType]
     * and [_isLoading] values accordingly.
     * @param characterType The character type.
     */
    fun getCharacters(characterType: String? = null) {
        _isLoading.value = true
        characterType?.let {
            viewModelScope.launch {
                characterUseCaseClass.invoke(characterType).collect {
                    _characterType.value = it
                    _isLoading.value = false
                }
            }
        } ?: run {
            _isLoading.value = false
            _characterType.value = UIState.ERROR(Exception("Type was null"))
        }
    }


    /**
     * This method is called when the user enters a text query to search for a character. It sets the [_textQuery] value
     * to the entered text.
     * @param text The text query entered by the user.
     */
    fun searchItems(text: String?) {
        text?.let {
            _textQuery.value = text

        }?: kotlin.run {
            _textQuery.value = "Query was null"
        }
    }
}
