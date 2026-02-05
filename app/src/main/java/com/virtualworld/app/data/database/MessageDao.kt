package com.virtualworld.app.data.database

import androidx.room.*
import com.virtualworld.app.data.model.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    
    @Query("SELECT * FROM messages WHERE (sender_id = :userId OR recipient_id = :userId) ORDER BY timestamp DESC")
    fun getMessages(userId: String): Flow<List<Message>>
    
    @Query("SELECT * FROM messages WHERE (sender_id = :userId AND recipient_id = :otherUserId) OR (sender_id = :otherUserId AND recipient_id = :userId) ORDER BY timestamp DESC")
    fun getConversation(userId: String, otherUserId: String): Flow<List<Message>>
    
    @Query("SELECT * FROM messages WHERE (sender_id = :userId AND recipient_id = :otherUserId) OR (sender_id = :otherUserId AND recipient_id = :userId) ORDER BY timestamp DESC")
    suspend fun getConversationList(userId: String, otherUserId: String): List<Message>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: Message)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(messages: List<Message>)
    
    @Update
    suspend fun updateMessage(message: Message)
    
    @Delete
    suspend fun deleteMessage(message: Message)
    
    @Query("DELETE FROM messages WHERE sender_id = :userId OR recipient_id = :userId")
    suspend fun deleteAllMessages(userId: String)
    
    @Query("SELECT COUNT(*) FROM messages WHERE recipient_id = :userId AND is_read = 0")
    fun getUnreadCount(userId: String): Flow<Int>
}
