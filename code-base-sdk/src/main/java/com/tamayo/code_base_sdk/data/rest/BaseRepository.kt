package com.tamayo.code_base_sdk.data.rest

import android.util.Log
import com.tamayo.code_base_sdk.domain.DomainCharacter
import com.tamayo.code_base_sdk.domain.mapToDomain
import com.tamayo.code_base_sdk.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * This interface provides a method to get the characters by making a request to
 * the API using the characterType and returning a flow of [UIState].
 */
interface BaseRepository {

    /**
     * This function fetches the characters from the API and returns a flow of [UIState] as a result.
     * @param charactersType The type of character to be fetched from the API.
     * @return A [Flow] of [UIState] which contains the list of [DomainCharacter] on [UIState.SUCCESS],
     * [UIState.LOADING] when request is being made and [UIState.ERROR] on error.
     */
    fun getCharacters(charactersType: String? = null): Flow<UIState<List<DomainCharacter>>>
}

/**
 * This class implements the [BaseRepository] interface and provides a method to fetch characters from the API.
 * @param service An instance of [BaseService] to make the network call.
 * @param ioDispatcher A dispatcher for running the coroutine on IO thread.
 */
class BaseRepositoryImpl @Inject constructor(
    private val service: BaseService,
    private val ioDispatcher: CoroutineDispatcher
) : BaseRepository {

    override fun getCharacters(charactersType: String?): Flow<UIState<List<DomainCharacter>>> = flow {
        emit(UIState.LOADING)
        try {
            charactersType?.let {
                val response = service.getCharacters(charactersType)

                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(UIState.SUCCESS(it.relatedTopics.mapToDomain()))
                    } ?: throw Exception("Response was null")

                } else {
                    throw Exception(response.errorBody()?.string())
                }

            } ?: kotlin.run {
                emit(UIState.ERROR(java.lang.Exception("Type was null")))
            }
        }catch (e: Exception){
            emit(
                UIState.ERROR(e)
            )

        }



    }.flowOn(ioDispatcher)

}
