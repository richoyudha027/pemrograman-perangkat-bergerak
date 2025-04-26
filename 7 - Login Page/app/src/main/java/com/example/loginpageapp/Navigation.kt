package com.example.loginpageapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.loginpageapp.ui.login.LoginPage
import com.example.loginpageapp.ui.signup.SignUpPage
import com.example.loginpageapp.ui.signup.Privacy
import com.example.loginpageapp.ui.signup.Policy
import com.example.loginpageapp.ui.HomePage

sealed class Route {
    data class LoginPage(val name: String = "Login") : Route()
    data class SignUpPage(val name: String = "SignUp") : Route()
    data class Privacy(val name: String = "Privacy") : Route()
    data class Policy(val name: String = "Policy") : Route()
    data class HomePage(val name: String = "Home") : Route()
}

@Composable
fun myNavigation(navHostController: NavHostController){
    NavHost(
        navController = navHostController,
        startDestination = "login_flow",
    ){
        navigation(startDestination = Route.LoginPage().name, route = "login_flow") {
            composable(Route.LoginPage().name) {
                LoginPage(
                    onLoginClick = {
                        navHostController.navigate(Route.HomePage().name) {
                            popUpTo(route = "login_flow")
                        }
                    },
                    onSignUpClick = {
                        navHostController.navigateToSingleTop(Route.SignUpPage().name)
                    }
                )
            }
            composable(Route.SignUpPage().name) {
                SignUpPage(
                    onSignUpClick = {
                        navHostController.navigate(Route.LoginPage().name)
                        { popUpTo("login_flow") }
                    },
                    onLoginClick = {
                        navHostController.navigate(Route.LoginPage().name)
                    },
                    onPolicyClick = {
                        navHostController.navigate(Route.Policy().name)
                        { launchSingleTop = true }
                    },
                    onPrivacyClick = {
                        navHostController.navigate(Route.Privacy().name)
                        { launchSingleTop = true }
                    }
                )
            }
            composable(route = Route.Policy().name){
                Policy {
                    navHostController.navigateUp()
                }
            }
            composable(route = Route.Privacy().name){
                Privacy {
                    navHostController.navigateUp()
                }
            }
        }
        composable(Route.HomePage().name){
            HomePage(
                onLogoutClick = {
                    navHostController.navigateToSingleTop(Route.LoginPage().name)
                }
            )
        }
    }
}

fun NavController.navigateToSingleTop(route: String){
    navigate(route){
        popUpTo(graph.findStartDestination().id){
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}