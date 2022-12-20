package com.nmihalek.diffcoverageexample.calculator

import javax.inject.Inject

class AdditionOperator @Inject constructor(): Operator {
    override fun apply(first: Int, second: Int): Int = first +  second
}
