package com.nmihalek.diffcoverageexample.calculator

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class MultiplyOperatorTest {

    lateinit var sut: MultiplyOperator

    @Before
    fun setUp() {
        sut = MultiplyOperator()
    }

    @Test
    fun `Given 2 and 3, when apply called, should return 6`() {
        val result = sut.apply(2, 3)

        assertEquals(6, result)
    }

    @Test
    fun `Given 3 and 2, when apply called, should return 6 - transitivity`() {
        val result = sut.apply(3, 2)

        assertEquals(6, result)
    }

    @Test
    fun `Given 2 and 2, when apply called, should return 4`() {
        val result = sut.apply(2, 2)

        assertEquals(4, result)
    }

    @Test
    fun `Given 1 and 2, when apply called, should return 2`() {
        val result = sut.apply(1, 2)

        assertEquals(2, result)
    }

    @Test
    fun `Given negative 2 and positive 3, when apply called, should return negative 6`() {
        val result = sut.apply(-2, 3)

        assertEquals(-6, result)
    }
}
