package com.tamayo.code_base_sdk.rest

import com.tamayo.code_base_sdk.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseService {

    /**
     * http://api.duckduckgo.com/?q=simpsons+characters&format=json
     */
    @GET
    suspend fun getCharacters(
        @Query("q") characterType: String,
        @Query("format") format: String = FORMAT,
    ): Response<CharacterResponse>

    companion object{
        const val BASE_URL = "http://api.duckduckgo.com/"
        const val IMAGE_BASE_URL = "https://duckduckgo.com"
        private const val FORMAT = "json"
    }
}