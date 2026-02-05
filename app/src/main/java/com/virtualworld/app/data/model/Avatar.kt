package com.virtualworld.app.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Avatar(
    @SerializedName("id")
    val id: String,
    
    @SerializedName("user_id")
    val userId: String,
    
    @SerializedName("body_type")
    val bodyType: String = "default",
    
    @SerializedName("skin_color")
    val skinColor: String = "#FFDBAC",
    
    @SerializedName("hair_style")
    val hairStyle: String = "short",
    
    @SerializedName("hair_color")
    val hairColor: String = "#000000",
    
    @SerializedName("eye_color")
    val eyeColor: String = "#0000FF",
    
    @SerializedName("outfit")
    val outfit: String = "casual_01",
    
    @SerializedName("accessories")
    val accessories: List<String> = emptyList(),
    
    @SerializedName("height")
    val height: Float = 1.75f,
    
    @SerializedName("position")
    val position: Position = Position(),
    
    @SerializedName("animation_state")
    val animationState: String = "idle"
) : Parcelable

@Parcelize
data class Position(
    @SerializedName("x")
    val x: Float = 0f,
    
    @SerializedName("y")
    val y: Float = 0f,
    
    @SerializedName("z")
    val z: Float = 0f,
    
    @SerializedName("rotation")
    val rotation: Float = 0f
) : Parcelable
