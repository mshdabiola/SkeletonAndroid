/*
 *abiola 2024
 */

package com.mshdabiola.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mshdabiola.model.Model

@Entity(tableName = "model_table")
data class ModelEntity(
    @PrimaryKey(true)
    val id: Long?,
    val name: String,
)

fun ModelEntity.asModel() = Model(id, name)
fun Model.asModelEntity() = ModelEntity(id, name)
