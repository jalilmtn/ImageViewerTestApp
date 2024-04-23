package com.example.imageviewertestapp.domain.repo

import androidx.paging.PagingData
import com.example.imageviewertestapp.domain.Cat
import kotlinx.coroutines.flow.Flow

interface CatsPagingRepo {
    fun getCats():  Flow<PagingData<Cat>>
}