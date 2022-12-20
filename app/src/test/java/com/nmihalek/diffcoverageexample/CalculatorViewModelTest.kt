package com.nmihalek.diffcoverageexample

import com.nmihalek.diffcoverageexample.calculator.AdditionOperator
import com.nmihalek.diffcoverageexample.calculator.MultiplyOperator
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CalculatorViewModelTest {

    lateinit var sut: CalculatorViewModel

    @Before
    fun setUp() {
        sut = CalculatorViewModel(MultiplyOperator(), AdditionOperator())
    }

    @Test
    fun `Given operator multiply and fields 2 and 3, when calculate pressed, should return 6 as result`() {
        sut.selectedOperator = CalculatorViewModel.Operators.Multiply
        sut.firstField = "2"
        sut.secondField = "3"

        sut.onCalculatePressed()

        assertEquals(6, sut.result)
    }

    @Test
    fun `Given operator add and fields 2 and 3, when calculate pressed, should return 5 as result`() {
        sut.selectedOperator = CalculatorViewModel.Operators.Add
        sut.firstField = "2"
        sut.secondField = "3"

        sut.onCalculatePressed()

        assertEquals(5, sut.result)
    }

    @Test
    fun `Given empty fields, when calculate pressed, should do nothing`() {
        sut.selectedOperator = CalculatorViewModel.Operators.Multiply

        sut.onCalculatePressed()

        assertNull(sut.result)
    }
}
