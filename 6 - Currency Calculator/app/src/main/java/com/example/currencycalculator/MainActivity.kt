package com.example.currencycalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currencycalculator.ui.theme.CurrencyCalculatorTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyCalculator()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyCalculator(){
    var num by remember { mutableStateOf("") }
    val currencyList = listOf(
        "USD", "EUR", "GBP", "JPY", "THB",
        "CHY", "KRW", "SGD", "KWD", "RUB"
    )
    var currency2 by remember { mutableStateOf(currencyList[1]) }
    var isExpanded by remember { mutableStateOf(false) }
    var stateVertical = rememberScrollState(0)
    var result by remember { mutableStateOf("0") }
    val exchangeRates = mapOf(
        "USD" to 0.000066,
        "EUR" to 0.000060,
        "GBP" to 0.000052,
        "JPY" to 0.0093,
        "THB" to 0.0024,
        "CHY" to 0.00045,
        "KRW" to 0.087,
        "SGD" to 0.000087,
        "KWD" to 0.000020,
        "RUB" to 0.0061
    )

    fun convertCurrency(){
        if(num.isNotEmpty()){
            val IDR = num.toDoubleOrNull() ?: 0.0
            val exchangeRate = exchangeRates[currency2] ?: 1.0
            val convertedAmount = IDR * exchangeRate
            result = if (convertedAmount % 1.0 == 0.0) {
                convertedAmount.toInt().toString()
            } else {
                "%.5f".format(convertedAmount).trimEnd('0').trimEnd('.')
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Currency Converter",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp),
        )

        Spacer(modifier = Modifier.padding(24.dp))

        TextField(
            value = num,
            modifier = Modifier
                .fillMaxWidth().padding(start = 23.dp, end = 24.dp),
            onValueChange = {
                num = it
                convertCurrency()
            },
            placeholder = {
                Text(text = "Masukkan nilai mata uang.")
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Row(modifier = Modifier.padding(16.dp)) {
            Box( modifier = Modifier
                    .width(120.dp)
                    .background(Color.Transparent)
                    .border(2.dp, Color.Gray, RoundedCornerShape(10.dp))
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "IDR",
                    fontSize = 17.sp,
                    style = TextStyle(textAlign = TextAlign.Center)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Image(
                painter = painterResource(id = R.drawable.pngwing_com),
                contentDescription = "exchange arrow",
                modifier = Modifier.size(50.dp).padding(4.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))


            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { isExpanded = !isExpanded }
            ) {
                TextField(
                    modifier = Modifier
                        .menuAnchor()
                        .width(120.dp),
                    value = currency2,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
                )

                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false },
                    modifier = Modifier.verticalScroll(stateVertical)
                ) {
                    currencyList.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            text = { Text(text = text) },
                            onClick = {
                                currency2 = currencyList[index]
                                isExpanded = false
                                convertCurrency()
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.padding(24.dp))

        Text(
            text = "RESULT",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(12.dp))

        Text(
            text = "${if (num.isNotEmpty()) num else "0"} IDR = $result $currency2",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
