package com.example.starbuck

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.starbuck.ui.navigation.Navigation
import com.example.starbuck.ui.theme.StarbuckTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StarbuckTheme {
                val navController = rememberNavController()
                Navigation(navController = navController)
            }
        }
    }
}