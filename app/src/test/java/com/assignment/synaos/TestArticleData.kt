package com.assignment.synaos

import com.assignment.synaos.data.model.ArticleResponseModel
import com.assignment.synaos.domain.model.ArticleDomainModel

object TestArticleData {
    fun getArticleData(): List<ArticleResponseModel> {
        return listOf(
            ArticleResponseModel(
                id = 1,
                featured = true,
                title = "test title",
                imageUrl = "url",
                summary = "test summary",
                newsSite = "test site",
                updatedAt = "test date",
                publishedAt = "test date",
                url = ""

            )

        )
    }

    fun getArticleDomainData(): List<ArticleDomainModel> {
        return listOf(
            ArticleDomainModel(
                id = 1,
                featured = true,
                title = "test title",
                imageUrl = "url",
                summary = "test summary",
                newsSite = "test site",
                updatedAt = "test date",
                publishedAt = "test date",
                url = ""

            )

        )
    }


}