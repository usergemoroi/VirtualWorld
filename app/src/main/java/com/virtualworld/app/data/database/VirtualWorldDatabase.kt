package com.virtualworld.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.virtualworld.app.data.model.Friend
import com.virtualworld.app.data.model.Message
import com.virtualworld.app.data.model.Purchase
import com.virtualworld.app.data.model.User

@Database(
    entities = [
        User::class,
        Friend::class,
        Message::class,
        Purchase::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class VirtualWorldDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun friendDao(): FriendDao
    abstract fun messageDao(): MessageDao
    abstract fun purchaseDao(): PurchaseDao
}
