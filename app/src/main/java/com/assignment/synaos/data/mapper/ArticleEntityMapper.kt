package com.assignment.synaos.data.mapper

import com.assignment.synaos.data.model.ArticleResponseModel
import com.assignment.synaos.domain.model.ArticleDomainModel

object ArticleEntityMapper {

    fun List<ArticleResponseModel>.toArticleDomainList(): List<ArticleDomainModel> {
        return map {
            ArticleDomainModel(
                it.id,
                it.featured,
                it.title,
                it.imageUrl,
                it.summary,
                it.newsSite,
                it.updatedAt,
                it.publishedAt,
                it.url
            )
        }

    }
}