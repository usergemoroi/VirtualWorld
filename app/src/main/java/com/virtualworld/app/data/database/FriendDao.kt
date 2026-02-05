package com.virtualworld.app.data.database

import androidx.room.*
import com.virtualworld.app.data.model.Friend
import com.virtualworld.app.data.model.FriendStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface FriendDao {
    
    @Query("SELECT * FROM friends WHERE user_id = :userId AND status = :status")
    fun getFriends(userId: String, status: FriendStatus = FriendStatus.ACCEPTED): Flow<List<Friend>>
    
    @Query("SELECT * FROM friends WHERE user_id = :userId AND status = :status")
    suspend fun getFriendsList(userId: String, status: FriendStatus = FriendStatus.ACCEPTED): List<Friend>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFriend(friend: Friend)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFriends(friends: List<Friend>)
    
    @Update
    suspend fun updateFriend(friend: Friend)
    
    @Delete
    suspend fun deleteFriend(friend: Friend)
    
    @Query("DELETE FROM friends WHERE user_id = :userId")
    suspend fun deleteAllFriends(userId: String)
    
    @Query("SELECT * FROM friends WHERE user_id = :userId AND friend_id = :friendId")
    suspend fun getFriend(userId: String, friendId: String): Friend?
}
