package com.assignment.synaos.domain.usecase
import com.assignment.synaos.domain.model.ArticleDomainModel
import com.assignment.synaos.domain.repository.Repository
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun getArticles(page: Int, size: Int): List<ArticleDomainModel> {
        return repository.getArticles(page, size)
    }

}