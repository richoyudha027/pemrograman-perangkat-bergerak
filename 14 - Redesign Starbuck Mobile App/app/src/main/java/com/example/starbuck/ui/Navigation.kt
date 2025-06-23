package com.example.starbuck.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.starbuck.ui.page.HomePage
import com.example.starbuck.ui.page.MenuPage
import com.example.starbuck.ui.page.CartPage
import com.example.starbuck.ui.page.AccountPage

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Menu : Screen("menu")
    object Cart : Screen("cart")
    object Account : Screen("account")
}

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomePage(
                onNavigateToMenu = { navController.navigate(Screen.Menu.route) },
                onNavigateToCart = { navController.navigate(Screen.Cart.route) },
                onNavigateToAccount = { navController.navigate(Screen.Account.route) },
                onNavigateToHome = {}
            )
        }

        composable(Screen.Menu.route) {
            MenuPage(
                onNavigateToHome = { navController.navigate(Screen.Home.route) },
                onNavigateToCart = { navController.navigate(Screen.Cart.route) },
                onNavigateToAccount = { navController.navigate(Screen.Account.route) },
                onNavigateToMenu = {}
            )
        }

        composable(Screen.Cart.route) {
            CartPage(
                onNavigateToHome = { navController.navigate(Screen.Home.route) },
                onNavigateToMenu = { navController.navigate(Screen.Menu.route) },
                onNavigateToAccount = { navController.navigate(Screen.Account.route) },
                onNavigateToCart = {}
            )
        }

        composable(Screen.Account.route) {
            AccountPage(
                onNavigateToHome = { navController.navigate(Screen.Home.route) },
                onNavigateToMenu = { navController.navigate(Screen.Menu.route) },
                onNavigateToCart = { navController.navigate(Screen.Cart.route) },
                onNavigateToAccount = {}
            )
        }
    }
}