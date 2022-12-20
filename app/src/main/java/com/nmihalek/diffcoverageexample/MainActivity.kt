package com.nmihalek.diffcoverageexample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Suppress("LongMethod", "FunctionNaming")
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun MainScreen() {
    val vm = hiltViewModel<CalculatorViewModel>()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusController = LocalFocusManager.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(verticalArrangement = Arrangement.Center) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    label = { Text("First value") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    value = vm.firstField,
                    onValueChange = {
                        vm.firstField = it
                    }
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    label = { Text("Second value") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        vm.onCalculatePressed()
                        keyboardController?.hide()
                        focusController.clearFocus()
                    }),
                    value = vm.secondField,
                    onValueChange = {
                        vm.secondField = it
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                )
                {
                    ElevatedButton(
                        enabled = vm.selectedOperator != CalculatorViewModel.Operators.Multiply,
                        onClick = {
                            vm.selectedOperator = CalculatorViewModel.Operators.Multiply
                            vm.onCalculatePressed()
                        })
                    {
                        Text(text = "Multiply")
                    }
                    ElevatedButton(
                        enabled = vm.selectedOperator != CalculatorViewModel.Operators.Add,
                        onClick = {
                        vm.selectedOperator = CalculatorViewModel.Operators.Add
                        vm.onCalculatePressed()
                    }) {
                        Text(text = "Add")
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Result: ")
                    Text(text = vm.result?.toString() ?: "", style = MaterialTheme.typography.titleLarge)
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onClick = vm::onCalculatePressed
                )
                {
                    Text(text = "Calculate result")
                }
            }
        }
    )
}
