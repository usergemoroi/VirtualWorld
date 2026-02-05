package com.virtualworld.app.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VirtualWorld(
    @SerializedName("id")
    val id: String,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("description")
    val description: String,
    
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    
    @SerializedName("world_type")
    val worldType: WorldType = WorldType.PUBLIC,
    
    @SerializedName("max_capacity")
    val maxCapacity: Int = 50,
    
    @SerializedName("current_users")
    val currentUsers: Int = 0,
    
    @SerializedName("creator_id")
    val creatorId: String,
    
    @SerializedName("created_at")
    val createdAt: Long = System.currentTimeMillis(),
    
    @SerializedName("tags")
    val tags: List<String> = emptyList(),
    
    @SerializedName("is_featured")
    val isFeatured: Boolean = false,
    
    @SerializedName("rating")
    val rating: Float = 0f,
    
    @SerializedName("visit_count")
    val visitCount: Int = 0
) : Parcelable

enum class WorldType {
    PUBLIC,
    PRIVATE,
    FRIENDS_ONLY,
    INVITE_ONLY
}
