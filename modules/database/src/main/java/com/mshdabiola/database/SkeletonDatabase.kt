package com.mshdabiola.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mshdabiola.database.dao.ModelDao
import com.mshdabiola.database.model.ModelEntity

@Database(
    entities = [ModelEntity::class],
    version = 1,
//    autoMigrations = [
//        //AutoMigration(from = 2, to = 3, spec = DatabaseMigrations.Schema2to3::class),
//
//                     ]
//    ,
    exportSchema = true,
)
@TypeConverters()
abstract class SkeletonDatabase : RoomDatabase() {

    abstract fun getModelDao(): ModelDao
//
//    abstract fun getPlayerDao(): PlayerDao
//
//    abstract fun getPawnDao(): PawnDao
}
