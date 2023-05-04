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
    fun `get characters when state is Success and retrieve the network call`(){
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
    @Test
    fun `get characters when endpoint is empty and state is Loading and success but the Body is empty`(){
        val endPoint = ""

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

        val emptyState = state[1] as UIState.SUCCESS
        assertEquals(2, state.size)
        assert(state[0] is UIState.LOADING)
        assert(state[1] is UIState.SUCCESS)
        assertTrue(emptyState.data.isEmpty())


        coVerify { mockServiceApi.getCharacters(endPoint) }


        job.cancel()


    }


    @Test
    fun `get characters when endpoint is null and state is Loading and Error`(){
        val endPoint: String? = null


        val state = mutableListOf<UIState<List<DomainCharacter>>>()
        val job = testScope.launch {
            testObject.getCharacters(endPoint).collect{
                state.add(it)
            }
        }

        val errorState = state[1] as UIState.ERROR
        assertEquals(2, state.size)
        assert(state[0] is UIState.LOADING)
        assert(state[1] is UIState.ERROR)
        assertEquals("Type was null",errorState.ERROR.localizedMessage)


        job.cancel()


    }
}