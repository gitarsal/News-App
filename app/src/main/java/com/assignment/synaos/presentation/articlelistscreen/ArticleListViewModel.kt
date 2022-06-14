package com.assignment.synaos.presentation.articlelistscreen

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.assignment.synaos.common.ArticlePagingDataSource
import com.assignment.synaos.domain.model.ArticleDomainModel
import com.assignment.synaos.domain.usecase.NewsUseCase
import com.assignment.synaos.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : BaseViewModel<ArticleListViewModel.ArticleUIState>() {
    fun getArticles() {

        viewModelScope.launch(Dispatchers.IO) {
            Pager(
                config = PagingConfig(
                    pageSize = 1
                ),
                pagingSourceFactory = {
                    ArticlePagingDataSource(newsUseCase)
                }
            ).flow.cachedIn(viewModelScope)
                .collect() { pageData ->
                    withContext(Dispatchers.Main)
                    {
                        stateLiveData.value = ArticleUIState.Loaded(pageData)
                    }
                }
        }
    }

    sealed class ArticleUIState {
        class Loaded(val articleList: PagingData<ArticleDomainModel>) : ArticleUIState()
    }

}

