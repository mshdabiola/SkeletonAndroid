/*
 *abiola 2024
 */

package com.mshdabiola.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mshdabiola.model.Note

@Entity(tableName = "note_table")
data class NoteEntity(
    @PrimaryKey(true)
    val id: Long?,
    val title: String,
    val content: String,
)

fun NoteEntity.asExternalNote() = Note(id, title, content)
