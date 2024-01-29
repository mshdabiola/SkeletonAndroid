/*
 *abiola 2022
 */

package com.mshdabiola.detail

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import com.mshdabiola.designsystem.component.SkTextField
import com.mshdabiola.ui.TrackScreenViewEvent
import kotlinx.coroutines.launch

@Composable
internal fun DetailRoute(
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    DetailScreen(
        onShowSnackbar = onShowSnackbar,
        modifier = modifier,
        title = viewModel.noteState.value.title,
        content = viewModel.noteState.value.content,
        onTitleChange = viewModel::onTitleChange,
        onContentChange = viewModel::onContentChange,

    )
}

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
@Composable
internal fun DetailScreen(
    modifier: Modifier = Modifier,
    title: String = "",
    content: String = "",
    onTitleChange: (String) -> Unit = {},
    onContentChange: (String) -> Unit = {},
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    val coroutineScope = rememberCoroutineScope()
    Column(modifier) {
        SkTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = onTitleChange,
            placeholder = "Title",
            maxNum = 1,
            imeAction = ImeAction.Next,
        )
        SkTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            value = content,
            onValueChange = onContentChange,
            placeholder = "content",
            imeAction = ImeAction.Done,
            keyboardAction = { coroutineScope.launch { onShowSnackbar("Note Update", null) } },
        )
    }

    TrackScreenViewEvent(screenName = "Detail")
}
