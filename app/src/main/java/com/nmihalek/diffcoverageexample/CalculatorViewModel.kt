package com.nmihalek.diffcoverageexample

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.nmihalek.diffcoverageexample.calculator.Operator
import com.nmihalek.diffcoverageexample.di.Addition
import com.nmihalek.diffcoverageexample.di.Multiplication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    @Multiplication
    private val multiplication: Operator,
    @Addition
    private val addition: Operator
): ViewModel() {

    var firstField by mutableStateOf("")
    var secondField by mutableStateOf("")
    var result by mutableStateOf<Int?>(null)
        private set
    var selectedOperator by mutableStateOf(Operators.Multiply)

    fun onCalculatePressed() {
        if (firstField.isEmpty() || secondField.isEmpty()) {
            return
        }
        result = when(selectedOperator) {
            Operators.Multiply -> multiplication.apply(firstField.toInt(),  secondField.toInt())
            Operators.Add -> addition.apply(firstField.toInt(), secondField.toInt())
        }
    }

    enum class Operators {
        Multiply, Add
    }
}
