package com.assignment.synaos.common

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.assignment.synaos.domain.model.ArticleDomainModel
import com.assignment.synaos.domain.usecase.NewsUseCase

class ArticlePagingDataSource(private val newsUseCase: NewsUseCase) :
    PagingSource<Int, ArticleDomainModel>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDomainModel> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = newsUseCase.getArticles(page * PAGE_SIZE, PAGE_SIZE)
            LoadResult.Page(
                data = response,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, ArticleDomainModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 0
        private const val PAGE_SIZE = 10
    }

}
