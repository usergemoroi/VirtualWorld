package com.virtualworld.app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "friends")
data class Friend(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    
    @SerializedName("user_id")
    val userId: String,
    
    @SerializedName("friend_id")
    val friendId: String,
    
    @SerializedName("friend_username")
    val friendUsername: String,
    
    @SerializedName("friend_display_name")
    val friendDisplayName: String,
    
    @SerializedName("friend_avatar_url")
    val friendAvatarUrl: String? = null,
    
    @SerializedName("status")
    val status: FriendStatus = FriendStatus.PENDING,
    
    @SerializedName("is_online")
    val isOnline: Boolean = false,
    
    @SerializedName("last_online")
    val lastOnline: Long = System.currentTimeMillis(),
    
    @SerializedName("created_at")
    val createdAt: Long = System.currentTimeMillis(),
    
    @SerializedName("current_world")
    val currentWorld: String? = null
) : Parcelable

enum class FriendStatus {
    PENDING,
    ACCEPTED,
    BLOCKED
}
