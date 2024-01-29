/*
 *abiola 2022
 */

package com.mshdabiola.ui

import android.content.Context
import android.net.Uri
import androidx.annotation.ColorInt
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mshdabiola.analytics.LocalAnalyticsHelper
import com.mshdabiola.designsystem.theme.SkTheme

/**
 * An extension on [LazyListScope] defining a feed with news resources.
 * Depending on the [feedState], this might emit no items.
 */
@OptIn(ExperimentalFoundationApi::class)
fun LazyStaggeredGridScope.noteItem(
    feedState: State,
    onClick: (Int) -> Unit = {},
) {
    when (feedState) {
        State.Loading -> Unit
        is State.Success -> {
            items(
                items = feedState.feed,
                key = { it.id },
                contentType = { "newsFeedItem" },
            ) { note ->
                val context = LocalContext.current
                val analyticsHelper = LocalAnalyticsHelper.current
                val backgroundColor = MaterialTheme.colorScheme.background.toArgb()

                NoteUi(
                    noteUiState = note,
                    onClick = {
                        analyticsHelper.logNoteOpened(
                            newsResourceId = note.id.toString(),
                        )
                        launchCustomChromeTab(context, Uri.parse(""), backgroundColor)
                    },
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .animateItemPlacement(),
                )
            }
        }
    }
}

@Composable
fun NoteUi(
    modifier: Modifier,
    noteUiState: NoteUiState,
    onClick: (Int) -> Unit,
) {
    Text(text = "Hello")
}

fun launchCustomChromeTab(context: Context, uri: Uri, @ColorInt toolbarColor: Int) {
    val customTabBarColor = CustomTabColorSchemeParams.Builder()
        .setToolbarColor(toolbarColor).build()
    val customTabsIntent = CustomTabsIntent.Builder()
        .setDefaultColorSchemeParams(customTabBarColor)
        .build()

    customTabsIntent.launchUrl(context, uri)
}

sealed interface State {
    data object Loading : State
    data class Success(
        val feed: List<NoteUiState>,
    ) : State
}

data class NoteUiState(
    val id: Long,
    val title: String,
    val description: String,
)

@Preview
@Composable
private fun NoteUiPreview() {
    SkTheme {
        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Adaptive(300.dp)) {
            noteItem(
                feedState = State.Loading,
            )
        }
    }
}
