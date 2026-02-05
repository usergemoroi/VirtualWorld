package com.virtualworld.app.data.repository

import com.virtualworld.app.data.api.VirtualWorldApi
import com.virtualworld.app.data.database.UserDao
import com.virtualworld.app.data.model.User
import com.virtualworld.app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val api: VirtualWorldApi,
    private val userDao: UserDao
) {
    
    fun getUserProfile(userId: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        
        // Emit cached data first
        val cachedUser = userDao.getUser(userId)
        if (cachedUser != null) {
            emit(Resource.Success(cachedUser))
        }
        
        // Fetch from network
        try {
            val response = api.getUserProfile(userId)
            if (response.isSuccessful && response.body() != null) {
                val user = response.body()!!
                userDao.insertUser(user)
                emit(Resource.Success(user))
            } else {
                if (cachedUser == null) {
                    emit(Resource.Error("Failed to load user: ${response.message()}"))
                }
            }
        } catch (e: Exception) {
            if (cachedUser == null) {
                emit(Resource.Error("Error: ${e.localizedMessage}"))
            }
        }
    }
    
    suspend fun updateUserProfile(user: User): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.updateUserProfile(user.id, user)
            if (response.isSuccessful && response.body() != null) {
                val updatedUser = response.body()!!
                userDao.updateUser(updatedUser)
                emit(Resource.Success(updatedUser))
            } else {
                emit(Resource.Error("Failed to update profile: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }
    
    suspend fun searchUsers(query: String): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.searchUsers(query)
            if (response.isSuccessful && response.body() != null) {
                emit(Resource.Success(response.body()!!))
            } else {
                emit(Resource.Error("Search failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }
}
