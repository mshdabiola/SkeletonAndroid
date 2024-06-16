/*
 *abiola 2023
 */

@file:Suppress("ktlint:standard:max-line-length")

package com.mshdabiola.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class NotePreviewParameterProvider : PreviewParameterProvider<List<NoteUiState>> {

    override val values: Sequence<List<NoteUiState>> = sequenceOf(
        listOf(
            NoteUiState(id = 7450L, title = "Dewayne", description = "Justan"),
            NoteUiState(id = 1352L, title = "Bjorn", description = "Daquan"),
            NoteUiState(id = 4476L, title = "Tonya", description = "Ivelisse"),
            NoteUiState(id = 6520L, title = "Raegan", description = "Katrena"),
            NoteUiState(id = 5136L, title = "Markis", description = "Giles"),
            NoteUiState(id = 6868L, title = "Virgilio", description = "Ashford"),
            NoteUiState(id = 7100L, title = "Larae", description = "Krystyn"),
            NoteUiState(id = 3210L, title = "Nigel", description = "Sergio"),
            NoteUiState(id = 7830L, title = "Kristy", description = "Jacobi"),
            NoteUiState(id = 1020L, title = "Kathlene", description = "Shlomo"),
            NoteUiState(id = 3365L, title = "Corin", description = "Ross"),
        ),
    )
}
