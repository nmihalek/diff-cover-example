package com.nmihalek.diffcoverageexample.calculator

interface Operator {
    fun apply(first: Int, second: Int): Int
}