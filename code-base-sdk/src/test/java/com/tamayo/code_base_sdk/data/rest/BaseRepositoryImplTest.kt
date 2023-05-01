package com.tamayo.code_base_sdk.data.rest

import com.tamayo.code_base_sdk.data.model.CharacterResponse
import com.tamayo.code_base_sdk.domain.DomainCharacter
import com.tamayo.code_base_sdk.utils.CharactersType
import com.tamayo.code_base_sdk.utils.UIState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BaseRepositoryImplTest {

    private lateinit var testObject: BaseRepository
    private val mockServiceApi = mockk<BaseService>(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val mockDomain = mockk<CharacterResponse>(relaxed = true)

    @Before
    fun setUp() {
        testObject = BaseRepositoryImpl(mockServiceApi, testDispatcher)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }


    @Test
    fun `get characters when state is Error`(){
        val endPoint = "simpsons characters"

        coEvery { mockServiceApi.getCharacters(endPoint) } returns mockk{
            every { isSuccessful } returns true
            every { body() } returns mockDomain

        }

        val state = mutableListOf<UIState<List<DomainCharacter>>>()
        val job = testScope.launch {
            testObject.getCharacters(endPoint).collect{
                state.add(it)
            }
        }

        assertEquals(2, state.size)
        assert(state[1] is UIState.SUCCESS)


        coVerify { mockServiceApi.getCharacters(endPoint) }


        job.cancel()


    }
}