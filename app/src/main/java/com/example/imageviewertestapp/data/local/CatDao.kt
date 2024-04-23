package com.example.imageviewertestapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCat(catEntity: CatEntity)

    @Query("SELECT * FROM catentity")
    fun getCats(): Flow<List<CatEntity>>

    @Query("SELECT * FROM catentity WHERE id = :id")
    suspend fun getCatById(id: String): CatEntity?
}