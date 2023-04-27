package com.tamayo.code_base_sdk.rest

import com.tamayo.code_base_sdk.domain.DomainCharacter
import com.tamayo.code_base_sdk.domain.mapToDomain
import com.tamayo.code_base_sdk.utils.UIState
import com.tamayo.code_base_sdk.utils.CharactersType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface BaseRepository {
    fun getCharacters(charactersType: CharactersType): Flow<UIState<List<DomainCharacter>>>
}

class BaseRepositoryImpl @Inject constructor(
    private val service: BaseService,
    private val ioDispatcher: CoroutineDispatcher
) : BaseRepository {
    override fun getCharacters(charactersType: CharactersType): Flow<UIState<List<DomainCharacter>>> = flow {
        emit(UIState.LOADING)
        try {
            val response = service.getCharacters(charactersType.realValue)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(UIState.SUCCESS(it.relatedTopics.mapToDomain()))
                }
            }

        }catch (e: Exception){
            emit(
                UIState.ERROR(e)
            )

        }


    }.flowOn(ioDispatcher)

}