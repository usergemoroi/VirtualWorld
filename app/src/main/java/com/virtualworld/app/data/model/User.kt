package com.virtualworld.app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    
    @SerializedName("email")
    val email: String,
    
    @SerializedName("username")
    val username: String,
    
    @SerializedName("display_name")
    val displayName: String,
    
    @SerializedName("bio")
    val bio: String? = null,
    
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    
    @SerializedName("level")
    val level: Int = 1,
    
    @SerializedName("experience_points")
    val experiencePoints: Int = 0,
    
    @SerializedName("virtual_currency")
    val virtualCurrency: Int = 0,
    
    @SerializedName("created_at")
    val createdAt: Long = System.currentTimeMillis(),
    
    @SerializedName("last_online")
    val lastOnline: Long = System.currentTimeMillis(),
    
    @SerializedName("is_online")
    val isOnline: Boolean = false,
    
    @SerializedName("friends_count")
    val friendsCount: Int = 0,
    
    @SerializedName("location")
    val location: String? = null
) : Parcelable
