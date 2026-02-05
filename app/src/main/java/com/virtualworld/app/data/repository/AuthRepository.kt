package com.virtualworld.app.data.repository

import com.virtualworld.app.data.api.LoginRequest
import com.virtualworld.app.data.api.RegisterRequest
import com.virtualworld.app.data.api.VirtualWorldApi
import com.virtualworld.app.data.local.PreferencesManager
import com.virtualworld.app.data.model.User
import com.virtualworld.app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val api: VirtualWorldApi,
    private val preferencesManager: PreferencesManager
) {
    
    suspend fun login(email: String, password: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.login(LoginRequest(email, password))
            if (response.isSuccessful && response.body() != null) {
                val authResponse = response.body()!!
                preferencesManager.saveAccessToken(authResponse.accessToken)
                preferencesManager.saveRefreshToken(authResponse.refreshToken)
                preferencesManager.saveUser(authResponse.user)
                emit(Resource.Success(authResponse.user))
            } else {
                emit(Resource.Error("Login failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Login error: ${e.localizedMessage}"))
        }
    }
    
    suspend fun register(
        email: String,
        password: String,
        username: String,
        displayName: String
    ): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.register(RegisterRequest(email, password, username, displayName))
            if (response.isSuccessful && response.body() != null) {
                val authResponse = response.body()!!
                preferencesManager.saveAccessToken(authResponse.accessToken)
                preferencesManager.saveRefreshToken(authResponse.refreshToken)
                preferencesManager.saveUser(authResponse.user)
                emit(Resource.Success(authResponse.user))
            } else {
                emit(Resource.Error("Registration failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Registration error: ${e.localizedMessage}"))
        }
    }
    
    suspend fun logout(): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.logout()
            if (response.isSuccessful) {
                preferencesManager.clearAll()
                emit(Resource.Success(Unit))
            } else {
                emit(Resource.Error("Logout failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            preferencesManager.clearAll()
            emit(Resource.Success(Unit))
        }
    }
    
    fun getCurrentUser(): Flow<User?> = preferencesManager.getUser()
    
    suspend fun isLoggedIn(): Boolean = preferencesManager.getAccessTokenSync() != null
}
