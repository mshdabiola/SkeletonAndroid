/*
 *abiola 2022
 */

package com.mshdabiola.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mshdabiola.data.repository.UserDataRepository
import com.mshdabiola.ui.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
) : ViewModel() {

    var shouldDisplayUndoBookmark by mutableStateOf(false)
    private var lastRemovedBookmarkId: String? = null

    val feedUiState: StateFlow<State> = flow<State> { State.Loading }
//        userNewsResourceRepository.observeAllBookmarked()
//            .map<List<UserNewsResource>, State>(State::Success)
//            .onStart { emit(Loading) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = State.Loading,
        )
}
