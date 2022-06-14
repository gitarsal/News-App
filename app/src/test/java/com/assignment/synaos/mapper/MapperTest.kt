package com.assignment.synaos.mapper

import com.assignment.synaos.TestArticleData
import com.assignment.synaos.data.mapper.ArticleEntityMapper.toArticleDomainList
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner
import kotlin.test.assertEquals

@RunWith(PowerMockRunner::class)
class MapperTest {
    @Test
    fun `test Article Mapper`(){
        assertEquals(TestArticleData.getArticleDomainData(), TestArticleData.getArticleData().toArticleDomainList())
    }
}