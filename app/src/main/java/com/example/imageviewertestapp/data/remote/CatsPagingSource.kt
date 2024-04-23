package com.example.imageviewertestapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imageviewertestapp.data.mapers.toCat
import com.example.imageviewertestapp.domain.Cat
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CatsPagingSource @Inject constructor(
    private val catApi: CatApi,
) : PagingSource<Int, Cat>() {
    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        return try {
            val page = params.key ?: 1
            val response = catApi.getCats(page = page, limit = 10).map { it.toCat() }

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1),
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}