package com.nmihalek.diffcoverageexample.calculator

import javax.inject.Inject

class MultiplyOperator @Inject constructor(): Operator {
    override fun apply(first: Int, second: Int): Int = first * second
}