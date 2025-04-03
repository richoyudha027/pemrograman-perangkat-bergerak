package com.example.simplecalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.LaunchedEffect

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleCalculator()
        }
    }
}

@Composable
fun SimpleCalculator() {
    var num1 by remember { mutableStateOf("0") }
    var num2 by remember { mutableStateOf("0") }
    var result by remember { mutableStateOf("0") }
    var showResult by remember {mutableStateOf<String?>(null) }

    val context = LocalContext.current

    Column (modifier = Modifier
        .fillMaxSize()
        .fillMaxWidth()
        .padding(top = 64.dp, start = 16.dp, end = 16.dp)
    ){
        TextField(value = num1, onValueChange = {
            num1 = it
        })

        TextField(value = num2, onValueChange = {
            num2 = it
        })
        Row (modifier = Modifier.padding(top = 16.dp)){
            Button(onClick = {
                result = (num1.toInt() + num2.toInt()).toString()
                showResult = "Result is $result"
            }) {
                Text(text = "Add")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                result = (num1.toInt() - num2.toInt()).toString()
                showResult = "Result is $result"
            }) {
                Text(text = "Sub")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                result = (num1.toInt() * num2.toInt()).toString()
                showResult = "Result is $result"
            }) {
                Text(text = "Mul")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                result = if (num1.toInt() % num2.toInt() == 0){
                    (num1.toInt() / num2.toInt()).toString()
                } else {
                    String.format("%.2f", num1.toInt() / num2.toDouble())
                }
                showResult = "Result is $result"
            }) {
                Text(text = "Div")
            }
        }
    }

    LaunchedEffect(showResult) {
        showResult?.let{
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            showResult = null
        }
    }
}