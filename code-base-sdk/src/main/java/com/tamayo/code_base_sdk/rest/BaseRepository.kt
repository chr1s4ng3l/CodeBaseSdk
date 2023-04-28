package com.tamayo.code_base_sdk.rest

import android.util.Log
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
    fun getCharacters(charactersType: String): Flow<UIState<List<DomainCharacter>>>
}

class BaseRepositoryImpl @Inject constructor(
    private val service: BaseService,
    private val ioDispatcher: CoroutineDispatcher
) : BaseRepository {
    override fun getCharacters(charactersType: String): Flow<UIState<List<DomainCharacter>>> = flow {
        emit(UIState.LOADING)
        try {
            val response = service.getCharacters(charactersType)
            if (response.isSuccessful){
                response.body()?.let {
                    Log.d("TAG", "getCharacters: $it")
                    emit(UIState.SUCCESS(it.relatedTopics.mapToDomain()))
                } ?: throw Exception("Response was null")

            }else{ throw Exception(response.errorBody()?.string())
            }

        }catch (e: Exception){
            emit(
                UIState.ERROR(e)
            )

        }


    }.flowOn(ioDispatcher)

}