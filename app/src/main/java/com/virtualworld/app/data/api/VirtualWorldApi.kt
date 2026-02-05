package com.virtualworld.app.data.api

import com.virtualworld.app.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface VirtualWorldApi {
    
    // Auth
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<AuthResponse>
    
    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<AuthResponse>
    
    @POST("auth/refresh")
    suspend fun refreshToken(
        @Body request: RefreshTokenRequest
    ): Response<AuthResponse>
    
    @POST("auth/logout")
    suspend fun logout(): Response<Unit>
    
    // User
    @GET("users/{userId}")
    suspend fun getUserProfile(
        @Path("userId") userId: String
    ): Response<User>
    
    @PUT("users/{userId}")
    suspend fun updateUserProfile(
        @Path("userId") userId: String,
        @Body user: User
    ): Response<User>
    
    @GET("users/search")
    suspend fun searchUsers(
        @Query("query") query: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<List<User>>
    
    // Friends
    @GET("friends/{userId}")
    suspend fun getFriends(
        @Path("userId") userId: String
    ): Response<List<Friend>>
    
    @POST("friends/request")
    suspend fun sendFriendRequest(
        @Body request: FriendRequest
    ): Response<Friend>
    
    @PUT("friends/{friendshipId}/accept")
    suspend fun acceptFriendRequest(
        @Path("friendshipId") friendshipId: String
    ): Response<Friend>
    
    @DELETE("friends/{friendshipId}")
    suspend fun removeFriend(
        @Path("friendshipId") friendshipId: String
    ): Response<Unit>
    
    // Messages
    @GET("messages/{userId}")
    suspend fun getMessages(
        @Path("userId") userId: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 50
    ): Response<List<Message>>
    
    @GET("messages/conversation/{otherUserId}")
    suspend fun getConversation(
        @Path("otherUserId") otherUserId: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 50
    ): Response<List<Message>>
    
    @POST("messages")
    suspend fun sendMessage(
        @Body message: Message
    ): Response<Message>
    
    @PUT("messages/{messageId}/read")
    suspend fun markMessageAsRead(
        @Path("messageId") messageId: String
    ): Response<Unit>
    
    // Worlds
    @GET("worlds")
    suspend fun getWorlds(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20,
        @Query("filter") filter: String? = null
    ): Response<List<VirtualWorld>>
    
    @GET("worlds/{worldId}")
    suspend fun getWorld(
        @Path("worldId") worldId: String
    ): Response<VirtualWorld>
    
    @GET("worlds/featured")
    suspend fun getFeaturedWorlds(): Response<List<VirtualWorld>>
    
    @POST("worlds/{worldId}/join")
    suspend fun joinWorld(
        @Path("worldId") worldId: String
    ): Response<Unit>
    
    @POST("worlds/{worldId}/leave")
    suspend fun leaveWorld(
        @Path("worldId") worldId: String
    ): Response<Unit>
    
    // Avatar
    @GET("avatars/{userId}")
    suspend fun getAvatar(
        @Path("userId") userId: String
    ): Response<Avatar>
    
    @PUT("avatars/{userId}")
    suspend fun updateAvatar(
        @Path("userId") userId: String,
        @Body avatar: Avatar
    ): Response<Avatar>
    
    // Purchases
    @GET("purchases/{userId}")
    suspend fun getPurchaseHistory(
        @Path("userId") userId: String
    ): Response<List<Purchase>>
    
    @POST("purchases")
    suspend fun createPurchase(
        @Body purchase: Purchase
    ): Response<Purchase>
}

// Request/Response models
data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val email: String,
    val password: String,
    val username: String,
    val displayName: String
)

data class RefreshTokenRequest(
    val refreshToken: String
)

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
    val user: User
)

data class FriendRequest(
    val userId: String,
    val friendId: String
)
