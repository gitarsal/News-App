package com.assignment.synaos.data.model



data class ArticleResponseModel(
    val id: Int,
    val featured: Boolean,
    val title: String,
    val imageUrl: String,
    val summary: String,
    val newsSite: String,
    val updatedAt: String,
    val publishedAt: String,
    val url: String

)
