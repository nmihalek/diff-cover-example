package com.nmihalek.diffcoverageexample.calculator

import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AdditionOperatorTest {

    lateinit var sut: AdditionOperator

    @Before
    fun setUp() {
        sut = AdditionOperator()
    }

    @Test
    fun `Given 2 and 3, when apply called, should return 5`() {
        val result = sut.apply(2, 3)

        TestCase.assertEquals(5, result)
    }

    @Test
    fun `Given 3 and 2, when apply called, should return 5 - transitivity`() {
        val result = sut.apply(3, 2)

        TestCase.assertEquals(5, result)
    }

    @Test
    fun `Given 2 and 2, when apply called, should return 4`() {
        val result = sut.apply(2, 2)

        TestCase.assertEquals(4, result)
    }

    @Test
    fun `Given 1 and 2, when apply called, should return 3`() {
        val result = sut.apply(1, 2)

        TestCase.assertEquals(3, result)
    }

    @Test
    fun `Given negative 2 and positive 3, when apply called, should return positive 1`() {
        val result = sut.apply(-2, 3)

        TestCase.assertEquals(1, result)
    }
}