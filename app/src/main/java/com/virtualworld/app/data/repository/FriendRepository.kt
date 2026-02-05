package com.virtualworld.app.data.repository

import com.virtualworld.app.data.api.FriendRequest
import com.virtualworld.app.data.api.VirtualWorldApi
import com.virtualworld.app.data.database.FriendDao
import com.virtualworld.app.data.model.Friend
import com.virtualworld.app.data.model.FriendStatus
import com.virtualworld.app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FriendRepository @Inject constructor(
    private val api: VirtualWorldApi,
    private val friendDao: FriendDao
) {
    
    fun getFriends(userId: String): Flow<Resource<List<Friend>>> = flow {
        emit(Resource.Loading())
        
        // Emit cached data first
        val cachedFriends = friendDao.getFriendsList(userId)
        if (cachedFriends.isNotEmpty()) {
            emit(Resource.Success(cachedFriends))
        }
        
        // Fetch from network
        try {
            val response = api.getFriends(userId)
            if (response.isSuccessful && response.body() != null) {
                val friends = response.body()!!
                friendDao.insertFriends(friends)
                emit(Resource.Success(friends))
            } else {
                if (cachedFriends.isEmpty()) {
                    emit(Resource.Error("Failed to load friends: ${response.message()}"))
                }
            }
        } catch (e: Exception) {
            if (cachedFriends.isEmpty()) {
                emit(Resource.Error("Error: ${e.localizedMessage}"))
            }
        }
    }
    
    suspend fun sendFriendRequest(userId: String, friendId: String): Flow<Resource<Friend>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.sendFriendRequest(FriendRequest(userId, friendId))
            if (response.isSuccessful && response.body() != null) {
                val friend = response.body()!!
                friendDao.insertFriend(friend)
                emit(Resource.Success(friend))
            } else {
                emit(Resource.Error("Failed to send friend request: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }
    
    suspend fun acceptFriendRequest(friendshipId: String): Flow<Resource<Friend>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.acceptFriendRequest(friendshipId)
            if (response.isSuccessful && response.body() != null) {
                val friend = response.body()!!
                friendDao.updateFriend(friend)
                emit(Resource.Success(friend))
            } else {
                emit(Resource.Error("Failed to accept friend request: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }
    
    suspend fun removeFriend(friendshipId: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.removeFriend(friendshipId)
            if (response.isSuccessful) {
                emit(Resource.Success(Unit))
            } else {
                emit(Resource.Error("Failed to remove friend: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }
    
    fun getFriendsFlow(userId: String): Flow<List<Friend>> = 
        friendDao.getFriends(userId, FriendStatus.ACCEPTED)
}
