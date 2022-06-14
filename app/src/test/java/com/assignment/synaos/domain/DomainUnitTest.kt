package com.assignment.synaos.domain

import com.assignment.synaos.TestArticleData
import com.assignment.synaos.data.remote.api.NewsApiService
import com.assignment.synaos.data.repository.RepositoryImp
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner
import kotlin.test.assertEquals

@RunWith(PowerMockRunner::class)
class DomainUnitTest {
    private val apiService = mock<NewsApiService>()
    private val repository = RepositoryImp(apiService)

    fun `test Repository Succeeds`() {
        runBlocking {
            whenever(
                repository.getArticles(1, 10)
            ).thenReturn(TestArticleData.getArticleDomainData())

            assertEquals(TestArticleData.getArticleDomainData(),  repository.getArticles(1, 10))
        }
    }
}