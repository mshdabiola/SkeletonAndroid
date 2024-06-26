/*
 *abiola 2022
 */

package com.mshdabiola.main

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.samples.apps.nowinandroid.core.designsystem.component.scrollbar.DraggableScrollbar
import com.google.samples.apps.nowinandroid.core.designsystem.component.scrollbar.rememberDraggableScroller
import com.google.samples.apps.nowinandroid.core.designsystem.component.scrollbar.scrollbarState
import com.mshdabiola.common.result.Result
import com.mshdabiola.designsystem.component.SkLoadingWheel
import com.mshdabiola.designsystem.component.SkTopAppBar
import com.mshdabiola.designsystem.icon.SkIcons
import com.mshdabiola.designsystem.theme.LocalTintTheme
import com.mshdabiola.designsystem.theme.SkTheme
import com.mshdabiola.ui.DevicePreviews
import com.mshdabiola.ui.NotePreviewParameterProvider
import com.mshdabiola.ui.NoteUiState
import com.mshdabiola.ui.TrackScrollJank
import com.mshdabiola.ui.noteItems

@Composable
internal fun MainRoute(
    onClick: (Long) -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val feedState by viewModel.feedUiMainState.collectAsStateWithLifecycle()
    MainScreen(
        mainState = feedState,
        onShowSnackbar = onShowSnackbar,
        modifier = modifier,
        onClick = onClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    mainState: Result<List<NoteUiState>>,
    onClick: (Long) -> Unit = {},
    onShowSnackbar: suspend (String, String?) -> Boolean = { _, _ -> false },
) {
    val state = rememberLazyListState()
    TrackScrollJank(scrollableState = state, stateName = "topic:screen")

    Scaffold(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        // contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            SkTopAppBar(
                titleRes = com.mshdabiola.designsystem.R.string.modules_designsystem_note,
                navigationIcon = SkIcons.Search,
                navigationIconContentDescription = "search",
                actionIcon = SkIcons.Settings,
                actionIconContentDescription = "setting",
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                ),
                onActionClick = { },
                onNavigationClick = { },
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.safeDrawing)
                    .testTag("main:add"),
                onClick = {
                    onClick(-1)
                },
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "add note",
                )
//                            Spacer(modifier = )
                Text("Add Note")
            }
        },
    ) { padding ->
        Box(
            modifier = Modifier.padding(padding),
        ) {
            LazyColumn(
                state = state,
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .testTag("main:list"),
            ) {
                item {
                    // Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
                }
                when (mainState) {
                    is Result.Loading -> item {
                        LoadingState()
                    }

                    is Result.Error -> TODO()
                    is Result.Success -> {
                        if (mainState.data.isEmpty()) {
                            item {
                                EmptyState()
                            }
                        } else {
                            noteItems(
                                items = mainState.data,
                                onNoteClick = onClick,
                                itemModifier = Modifier,
                            )
                        }
                    }
                }
                item {
                    Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
                }
            }
            val itemsAvailable = noteUiStateItemsSize(mainState)
            val scrollbarState = state.scrollbarState(
                itemsAvailable = itemsAvailable,
            )
            state.DraggableScrollbar(
                modifier = Modifier
                    .fillMaxHeight()
                    .windowInsetsPadding(WindowInsets.systemBars)
                    .padding(horizontal = 2.dp)
                    .align(Alignment.CenterEnd),
                state = scrollbarState,
                orientation = Orientation.Vertical,
                onThumbMoved = state.rememberDraggableScroller(
                    itemsAvailable = itemsAvailable,
                ),
            )
        }
    }
}

@Composable
private fun LoadingState(modifier: Modifier = Modifier) {
    SkLoadingWheel(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize()
            .testTag("main:loading"),
        contentDesc = stringResource(id = R.string.features_main_loading),
    )
}

@Composable
private fun EmptyState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .testTag("main:empty"),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val iconTint = LocalTintTheme.current.iconTint
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.features_main_img_empty_bookmarks),
            colorFilter = if (iconTint != Color.Unspecified) ColorFilter.tint(iconTint) else null,
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = stringResource(id = R.string.features_main_empty_error),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = R.string.features_main_empty_description),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

private fun noteUiStateItemsSize(
    topicUiState: Result<List<NoteUiState>>,
) = when (topicUiState) {
    is Result.Error -> 0 // Nothing
    is Result.Loading -> 1 // Loading bar
    is Result.Success -> topicUiState.data.size + 2
}

@DevicePreviews
@Composable
private fun LoadingStatePreview() {
    SkTheme {
        MainScreen(mainState = Result.Loading)
    }
}

@DevicePreviews
@Composable
private fun MainListPreview(
    @PreviewParameter(NotePreviewParameterProvider::class)
    notes: List<NoteUiState>,
) {
    SkTheme {
        MainScreen(mainState = Result.Success(notes))
    }
}

@DevicePreviews
@Composable
private fun EmptyStatePreview() {
    SkTheme {
        MainScreen(mainState = Result.Success(listOf()))
    }
}
