package com.assignment.synaos.data.repository

import com.assignment.synaos.data.mapper.ArticleEntityMapper.toArticleDomainList
import com.assignment.synaos.data.remote.api.NewsApiService
import com.assignment.synaos.domain.model.ArticleDomainModel
import com.assignment.synaos.domain.repository.Repository
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val apiService: NewsApiService

) : Repository {
    override suspend fun getArticles(page: Int, size: Int): List<ArticleDomainModel> {
        val response = apiService.getArticles(page, size)
        return response.toArticleDomainList()
    }





}