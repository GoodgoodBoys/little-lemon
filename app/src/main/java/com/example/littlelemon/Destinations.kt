package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import com.example.littlelemon.HomeScreen
import com.example.littlelemon.ProfileScreen
import com.example.littlelemon.OnboardingScreen

object Screen {
    val Home: @Composable (navController: NavController, MenuItemItem: List<MenuItemEntity>) -> Unit = { navController, MenuItemItem -> HomeScreen(navController, MenuItemItem) }
    val Onboarding: @Composable (navController: NavController, context: Context) -> Unit = { navController, context -> OnboardingScreen(navController, context) }
    val Profile: @Composable (navController: NavController, context: Context) -> Unit = { navController, context -> ProfileScreen(navController, context) }
}