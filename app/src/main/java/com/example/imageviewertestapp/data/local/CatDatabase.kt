package com.example.imageviewertestapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CatEntity::class],
    version = 1
)
abstract class CatDatabase : RoomDatabase() {
    abstract val dao : CatDao
    companion object{
        const val DB_NAME = "cat.db"
    }
}