package com.assignment.synaos.data

import com.assignment.synaos.TestArticleData
import com.assignment.synaos.data.remote.api.NewsApiService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner
import kotlin.test.assertEquals

@RunWith(PowerMockRunner::class)
class DataUnitTest {
    private val apiService = mock<NewsApiService>()
    fun `test Article Api Succeeds`() {
        runBlocking {

            whenever(
                apiService.getArticles(1, 10)
            ).thenReturn(TestArticleData.getArticleData())

            assertEquals(TestArticleData.getArticleData(), apiService.getArticles(1, 10))
        }
    }
    @Test
    fun `test Article Api failed`(): Unit = runBlocking {
        val error = NullPointerException("Some thing went wrong")
        whenever(
            apiService.getArticles(1, 10)
        ).thenThrow(error)
        try {
            apiService.getArticles(1, 10)
        }catch (e:NullPointerException)
        {
            assertEquals("Some thing went wrong", e.message)
        }
    }
}