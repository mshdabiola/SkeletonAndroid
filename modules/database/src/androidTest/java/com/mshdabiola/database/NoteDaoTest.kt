/*
 *abiola 2024
 */

package com.mshdabiola.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.mshdabiola.database.dao.NoteDao
import com.mshdabiola.database.model.NoteEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class NoteDaoTest {
    private lateinit var noteDao: NoteDao
    private lateinit var db: SkeletonDatabase

    @Before
    fun createDb() {
        val content = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(content, SkeletonDatabase::class.java).build()
        noteDao = db.getNoteDao()
    }

    @After
    fun closeDb() = db.close()

    @Test
    fun insertNote_getIt() = runTest {
        noteDao.upsert(NoteEntity(id = null, title = "abiola", content = "contetn"))
        noteDao.upsert(NoteEntity(id = null, title = "abiola", content = "contetn"))
        noteDao.upsert(NoteEntity(id = null, title = "abiola", content = "contetn"))

        assertEquals(3, noteDao.getAll().first().size)
    }
}
