package com.example.imageviewertestapp.domain.repo

import com.example.imageviewertestapp.data.local.CatEntity
import kotlinx.coroutines.flow.Flow

interface CatLocalRepo {
    fun getCats(): Flow<List<CatEntity>>
    suspend fun addCat(cat: CatEntity)
    suspend fun getCat(id: String): CatEntity?
}