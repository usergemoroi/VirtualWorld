package com.virtualworld.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.virtualworld.app.presentation.screens.auth.LoginScreen
import com.virtualworld.app.presentation.screens.auth.RegisterScreen
import com.virtualworld.app.presentation.screens.avatar.AvatarCustomizationScreen
import com.virtualworld.app.presentation.screens.friends.FriendsScreen
import com.virtualworld.app.presentation.screens.home.HomeScreen
import com.virtualworld.app.presentation.screens.messages.ConversationScreen
import com.virtualworld.app.presentation.screens.messages.MessagesScreen
import com.virtualworld.app.presentation.screens.profile.ProfileScreen
import com.virtualworld.app.presentation.screens.purchase.PurchaseHistoryScreen
import com.virtualworld.app.presentation.screens.settings.SettingsScreen
import com.virtualworld.app.presentation.screens.splash.SplashScreen
import com.virtualworld.app.presentation.screens.world.WorldScreen

@Composable
fun VirtualWorldNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        
        composable(Screen.Register.route) {
            RegisterScreen(navController = navController)
        }
        
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        
        composable(
            route = Screen.World.route,
            arguments = listOf(navArgument("worldId") { type = NavType.StringType })
        ) { backStackEntry ->
            val worldId = backStackEntry.arguments?.getString("worldId") ?: ""
            WorldScreen(navController = navController, worldId = worldId)
        }
        
        composable(
            route = Screen.Profile.route,
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            ProfileScreen(navController = navController, userId = userId)
        }
        
        composable(Screen.Friends.route) {
            FriendsScreen(navController = navController)
        }
        
        composable(Screen.Messages.route) {
            MessagesScreen(navController = navController)
        }
        
        composable(
            route = Screen.Conversation.route,
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            ConversationScreen(navController = navController, userId = userId)
        }
        
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
        
        composable(Screen.AvatarCustomization.route) {
            AvatarCustomizationScreen(navController = navController)
        }
        
        composable(Screen.PurchaseHistory.route) {
            PurchaseHistoryScreen(navController = navController)
        }
    }
}
