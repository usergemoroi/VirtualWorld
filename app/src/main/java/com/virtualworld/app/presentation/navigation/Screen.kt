package com.virtualworld.app.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object World : Screen("world/{worldId}") {
        fun createRoute(worldId: String) = "world/$worldId"
    }
    object Profile : Screen("profile/{userId}") {
        fun createRoute(userId: String) = "profile/$userId"
    }
    object Friends : Screen("friends")
    object Messages : Screen("messages")
    object Conversation : Screen("conversation/{userId}") {
        fun createRoute(userId: String) = "conversation/$userId"
    }
    object Settings : Screen("settings")
    object AvatarCustomization : Screen("avatar_customization")
    object PurchaseHistory : Screen("purchase_history")
}
