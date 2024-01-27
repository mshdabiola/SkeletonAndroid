package com.mshdabiola.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mshdabiola.database.dao.ModelDao
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ModelDaoTest {
    private lateinit var modelDao: ModelDao
    private lateinit var db: SkeletonDatabase

    @Before
    fun createDb() {
        val content = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(content, SkeletonDatabase::class.java).build()
        modelDao = db.getModelDao()
    }

    @Test
    fun upsertTest() = runTest {
    }

    @Test
    fun deleteTest() = runTest {
    }

    @Test
    fun deleteByIdTest() = runTest {
    }
}
