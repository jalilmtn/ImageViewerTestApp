package com.example.imageviewertestapp.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.imageviewertestapp.data.remote.CatsPagingSource
import com.example.imageviewertestapp.data.remote.CatsPagingSource.Companion.PAGE_LIMIT
import com.example.imageviewertestapp.domain.repo.CatsPagingRepo
import javax.inject.Inject

class CatsPagingRepoImpl @Inject constructor(private val pagingSource: CatsPagingSource) : CatsPagingRepo {
    override fun getCats() = Pager(
        config = PagingConfig(
            pageSize = PAGE_LIMIT,
        ),
        pagingSourceFactory = {
            pagingSource
        }
    ).flow
}