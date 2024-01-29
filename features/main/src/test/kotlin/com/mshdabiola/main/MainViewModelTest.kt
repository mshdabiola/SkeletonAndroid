/*
 *abiola 2022
 */

package com.mshdabiola.main

import com.mshdabiola.testing.repository.TestUserDataRepository
import com.mshdabiola.testing.util.MainDispatcherRule
import com.mshdabiola.ui.State.Loading
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class MainViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val userDataRepository = TestUserDataRepository()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel(
            userDataRepository = userDataRepository,
        )
    }

    @Test
    fun stateIsInitiallyLoading() = runTest {
        assertEquals(Loading, viewModel.feedUiState.value)
    }

    @Test
    fun oneBookmark_showsInFeed() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.feedUiState.collect() }
//
//        newsRepository.sendNewsResources(newsResourcesTestData)
//        userDataRepository.updateNewsResourceBookmark(newsResourcesTestData[0].id, true)
//        val item = viewModel.feedUiState.value
//        assertIs<Success>(item)
//        assertEquals(item.feed.size, 1)
//
//        collectJob.cancel()
    }
}
