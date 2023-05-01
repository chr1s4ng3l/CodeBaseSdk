package com.tamayo.code_base_sdk.data.rest

import com.tamayo.code_base_sdk.data.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Defines a base service interface for making API calls to DuckDuckGo API.
 * This interface provides methods for getting characters information.
 */
interface BaseService {

    /**
     * Fetches characters information from the API.
     * @param characterType the type of the character to search for.
     * @param format the format of the API response, defaults to json.
     * @return a [Response] object with the [CharacterResponse] data.
    */
    @GET("/")
    suspend fun getCharacters(
        @Query("q") characterType: String,
        @Query("format") format: String = FORMAT,
    ): Response<CharacterResponse>

    companion object {
        const val BASE_URL = "http://api.duckduckgo.com/"
        const val IMAGE_BASE_URL = "https://duckduckgo.com"
        private const val FORMAT = "json"
    }
}