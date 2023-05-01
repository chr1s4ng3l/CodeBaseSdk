package com.tamayo.code_base_sdk.domain.usecases

import com.tamayo.code_base_sdk.domain.DomainCharacter
import com.tamayo.code_base_sdk.data.rest.BaseRepository
import com.tamayo.code_base_sdk.utils.CharactersType
import com.tamayo.code_base_sdk.utils.UIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * This class represents a use case that
 * fetches characters from a repository and returns the results in a [Flow] of [UIState].
 * @param repository The repository used to fetch the characters.
 */
class CharacterUseCaseClass @Inject constructor(
    private val repository: BaseRepository
) {

    /**
     * Invokes the use case and returns a [Flow] of [UIState] containing a list of [DomainCharacter].
     * @param charactersType The type of characters to fetch.
     * @return A [Flow] of [UIState] containing a list of [DomainCharacter].
     */
    operator fun invoke(charactersType: String): Flow<UIState<List<DomainCharacter>>> {
        return repository.getCharacters(charactersType)

    }
}