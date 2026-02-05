package com.virtualworld.app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "messages")
data class Message(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    
    @SerializedName("sender_id")
    val senderId: String,
    
    @SerializedName("sender_name")
    val senderName: String,
    
    @SerializedName("sender_avatar_url")
    val senderAvatarUrl: String? = null,
    
    @SerializedName("recipient_id")
    val recipientId: String? = null,
    
    @SerializedName("group_id")
    val groupId: String? = null,
    
    @SerializedName("content")
    val content: String,
    
    @SerializedName("message_type")
    val messageType: MessageType = MessageType.TEXT,
    
    @SerializedName("timestamp")
    val timestamp: Long = System.currentTimeMillis(),
    
    @SerializedName("is_read")
    val isRead: Boolean = false,
    
    @SerializedName("is_deleted")
    val isDeleted: Boolean = false,
    
    @SerializedName("attachment_url")
    val attachmentUrl: String? = null
) : Parcelable

enum class MessageType {
    TEXT,
    IMAGE,
    VOICE,
    SYSTEM
}
