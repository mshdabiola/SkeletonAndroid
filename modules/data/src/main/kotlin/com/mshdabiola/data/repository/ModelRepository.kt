/*
 *abiola 2024
 */

package com.mshdabiola.data.repository

import com.mshdabiola.model.Model
import kotlinx.coroutines.flow.Flow

interface ModelRepository {
    suspend fun insertModel(model: Model)
    fun getModels(): Flow<List<Model>>
}
