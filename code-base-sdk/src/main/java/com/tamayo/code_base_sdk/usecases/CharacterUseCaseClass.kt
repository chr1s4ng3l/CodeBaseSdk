package com.tamayo.code_base_sdk.usecases

import com.tamayo.code_base_sdk.domain.DomainCharacter
import com.tamayo.code_base_sdk.rest.BaseRepository
import com.tamayo.code_base_sdk.utils.CharactersType
import com.tamayo.code_base_sdk.utils.UIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterUseCaseClass @Inject constructor(
    private val repository: BaseRepository
) {

    operator fun invoke(charactersType: CharactersType): Flow<UIState<List<DomainCharacter>>> {
        return repository.getCharacters(charactersType)

    }
}