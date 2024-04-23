package com.example.imageviewertestapp.data.repo

import com.example.imageviewertestapp.data.local.CatDao
import com.example.imageviewertestapp.data.local.CatEntity
import com.example.imageviewertestapp.domain.repo.CatLocalRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatLocalRepoImpl @Inject constructor(private val catDao: CatDao) : CatLocalRepo {
    override fun getCats(): Flow<List<CatEntity>> {
        return catDao.getCats()
    }

    //TODO: Inject Dispatchers for test
    override suspend fun addCat(cat: CatEntity) {
        withContext(Dispatchers.IO) {
            catDao.insertCat(cat)
        }
    }

    override suspend fun getCat(id: String): CatEntity? {
        return withContext(Dispatchers.IO) { catDao.getCatById(id) }
    }
}