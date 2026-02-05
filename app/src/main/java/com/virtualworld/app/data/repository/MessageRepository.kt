package com.virtualworld.app.data.repository

import com.virtualworld.app.data.api.VirtualWorldApi
import com.virtualworld.app.data.database.MessageDao
import com.virtualworld.app.data.model.Message
import com.virtualworld.app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageRepository @Inject constructor(
    private val api: VirtualWorldApi,
    private val messageDao: MessageDao
) {
    
    fun getMessages(userId: String): Flow<List<Message>> = 
        messageDao.getMessages(userId)
    
    fun getConversation(userId: String, otherUserId: String): Flow<Resource<List<Message>>> = flow {
        emit(Resource.Loading())
        
        // Emit cached data first
        val cachedMessages = messageDao.getConversationList(userId, otherUserId)
        if (cachedMessages.isNotEmpty()) {
            emit(Resource.Success(cachedMessages))
        }
        
        // Fetch from network
        try {
            val response = api.getConversation(otherUserId)
            if (response.isSuccessful && response.body() != null) {
                val messages = response.body()!!
                messageDao.insertMessages(messages)
                emit(Resource.Success(messages))
            } else {
                if (cachedMessages.isEmpty()) {
                    emit(Resource.Error("Failed to load messages: ${response.message()}"))
                }
            }
        } catch (e: Exception) {
            if (cachedMessages.isEmpty()) {
                emit(Resource.Error("Error: ${e.localizedMessage}"))
            }
        }
    }
    
    suspend fun sendMessage(message: Message): Flow<Resource<Message>> = flow {
        emit(Resource.Loading())
        try {
            // Save locally first
            messageDao.insertMessage(message)
            
            // Send to server
            val response = api.sendMessage(message)
            if (response.isSuccessful && response.body() != null) {
                val sentMessage = response.body()!!
                messageDao.updateMessage(sentMessage)
                emit(Resource.Success(sentMessage))
            } else {
                emit(Resource.Error("Failed to send message: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }
    
    suspend fun markMessageAsRead(messageId: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.markMessageAsRead(messageId)
            if (response.isSuccessful) {
                emit(Resource.Success(Unit))
            } else {
                emit(Resource.Error("Failed to mark message as read: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }
    
    fun getUnreadCount(userId: String): Flow<Int> = 
        messageDao.getUnreadCount(userId)
}
