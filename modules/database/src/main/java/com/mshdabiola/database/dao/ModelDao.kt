/*
 *abiola 2024
 */

package com.mshdabiola.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mshdabiola.database.model.ModelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ModelDao {

    @Upsert
    fun upsert(modelEntity: ModelEntity)

    @Query("SELECT * FROM model_table")
    fun getModel(): Flow<List<ModelEntity>>

    @Query("SELECT * FROM model_table")
    fun getNewModel(): PagingSource<Int, ModelEntity>
}
