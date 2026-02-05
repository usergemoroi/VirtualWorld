package com.virtualworld.app.data.database

import androidx.room.*
import com.virtualworld.app.data.model.Purchase
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseDao {
    
    @Query("SELECT * FROM purchases WHERE user_id = :userId ORDER BY purchase_date DESC")
    fun getPurchases(userId: String): Flow<List<Purchase>>
    
    @Query("SELECT * FROM purchases WHERE user_id = :userId ORDER BY purchase_date DESC")
    suspend fun getPurchasesList(userId: String): List<Purchase>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPurchase(purchase: Purchase)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPurchases(purchases: List<Purchase>)
    
    @Update
    suspend fun updatePurchase(purchase: Purchase)
    
    @Delete
    suspend fun deletePurchase(purchase: Purchase)
    
    @Query("DELETE FROM purchases WHERE user_id = :userId")
    suspend fun deleteAllPurchases(userId: String)
    
    @Query("SELECT * FROM purchases WHERE id = :purchaseId")
    suspend fun getPurchase(purchaseId: String): Purchase?
}
