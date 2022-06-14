package com.assignment.synaos.common

import com.assignment.synaos.common.ExtensionFunctions.getFormattedDate
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner
import kotlin.test.assertEquals

@RunWith(PowerMockRunner::class)
class ExtensionFunctionTest {
    @Test
    fun `test date converter`() {
        val date="2022-06-12T15:01:38.000Z"
        assertEquals("12 Jun 2022  03:01 PM", date.getFormattedDate())
    }
}