package com.tamayo.code_base_sdk.presentation.viewmodel

import com.tamayo.code_base_sdk.domain.DomainCharacter
import com.tamayo.code_base_sdk.domain.usecases.CharacterUseCaseClass
import com.tamayo.code_base_sdk.utils.CharactersType
import com.tamayo.code_base_sdk.utils.UIState
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainBaseViewModelTest {

    private lateinit var testObject: MainBaseViewModel
    private val mockUseCase = mockk<CharacterUseCaseClass>(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {

        Dispatchers.setMain(testDispatcher)
        testObject = MainBaseViewModel(mockUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }


    @Test
    fun `get characters when use case is correct and provides loading and success state`() {
        //AAA

        val characterType = CharactersType.SIMPSONS.realValue


        //Given
        every {
            mockUseCase(charactersType = characterType)

            //When
        } returns flowOf(
            UIState.SUCCESS(
                listOf(mockk(), mockk(), mockk())
            )
        )

        val state = mutableListOf<UIState<List<DomainCharacter>>>()
        val job = testScope.launch {
            testObject.characterType.collect {
                state.add(it)

            }

        }

        testObject.getCharacters(characterType)


        //Then
        assertEquals(2, state.size)
        assert(state[0] is UIState.LOADING)
        assert(state[1] is UIState.SUCCESS)
        val success = (state[1] as UIState.SUCCESS).data
        assertEquals(3, success.size)


        job.cancel()


    }

    @Test
    fun `get characters when use case is empty and only provides Loading state`() {
        //AAA

        val characterType = ""

        val state = mutableListOf<UIState<List<DomainCharacter>>>()
        val job = testScope.launch {
            testObject.characterType.collect {
                state.add(it)

            }

        }

        testObject.getCharacters(characterType)


        //Then
        assertEquals(1, state.size)
        assert(state[0] is UIState.LOADING)


        job.cancel()


    }

    @Test
    fun `get characters when type is null and provides Loading and Error state`() {
        //AAA

        val state = mutableListOf<UIState<List<DomainCharacter>>>()
        val job = testScope.launch {

            testObject.characterType.collect {
                state.add(it)

            }

        }

        testObject.getCharacters(null)


        //Then
        val errorState = state[1] as UIState.ERROR
        assertTrue(state.size == 2)
        assert(state[0] is UIState.LOADING)
        assert(state[1] is UIState.ERROR)
        assertEquals("Type was null", errorState.ERROR.localizedMessage)


        job.cancel()


    }


    @Test
    fun `search character when pass the query and display the item`() {
        val query = "Bart"
        testObject.searchItems(query)

        val queryState = testObject.textQuery.value

        assertEquals(queryState, query)

    }


    @Test
    fun `search character when query is null`() {
        val query: String? = null
        testObject.searchItems(query)

        val queryState = testObject.textQuery.value

        assertEquals(queryState, "Query was null")

    }

}