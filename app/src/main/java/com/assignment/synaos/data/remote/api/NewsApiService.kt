package com.assignment.synaos.data.remote.api

import com.assignment.synaos.data.model.ArticleResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("articles")
    suspend fun getArticles(@Query("_start") page: Int, @Query("_limit") size: Int): List<ArticleResponseModel>
}