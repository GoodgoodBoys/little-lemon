package com.example.littlelemon

import android.content.SharedPreferences
import com.example.littlelemon.Screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.content.Context
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun MyNavigation(navController: NavHostController, context: Context, menuItemItem: List<MenuItemEntity>) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    val isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false)

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) "home" else "onboarding"
    ) {
        composable("onboarding") { Screen.Onboarding(navController, context) }
        composable("home") { Screen.Home(navController, menuItemItem) }
        composable("profile") { Screen.Profile(navController, context) }
    }
}
