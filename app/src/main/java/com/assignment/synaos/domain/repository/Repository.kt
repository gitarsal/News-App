package com.assignment.synaos.domain.repository
import com.assignment.synaos.domain.model.ArticleDomainModel

interface Repository {
    suspend fun getArticles(page: Int, size: Int): List<ArticleDomainModel>
}