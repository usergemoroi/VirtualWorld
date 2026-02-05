package com.virtualworld.app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "purchases")
data class Purchase(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    
    @SerializedName("user_id")
    val userId: String,
    
    @SerializedName("item_id")
    val itemId: String,
    
    @SerializedName("item_name")
    val itemName: String,
    
    @SerializedName("item_type")
    val itemType: PurchaseItemType,
    
    @SerializedName("amount")
    val amount: Double,
    
    @SerializedName("currency")
    val currency: String = "USD",
    
    @SerializedName("virtual_currency_amount")
    val virtualCurrencyAmount: Int? = null,
    
    @SerializedName("purchase_date")
    val purchaseDate: Long = System.currentTimeMillis(),
    
    @SerializedName("status")
    val status: PurchaseStatus = PurchaseStatus.COMPLETED,
    
    @SerializedName("receipt")
    val receipt: String? = null
) : Parcelable

enum class PurchaseItemType {
    VIRTUAL_CURRENCY,
    AVATAR_ITEM,
    WORLD_ACCESS,
    PREMIUM_FEATURE
}

enum class PurchaseStatus {
    PENDING,
    COMPLETED,
    FAILED,
    REFUNDED
}
