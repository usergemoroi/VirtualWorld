package com.virtualworld.app.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.virtualworld.app.data.api.VirtualWorldApi
import com.virtualworld.app.data.database.*
import com.virtualworld.app.data.local.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()
    
    @Provides
    @Singleton
    fun provideOkHttpClient(
        preferencesManager: PreferencesManager
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                
                // Add auth token
                val token = preferencesManager.getAccessTokenSync()
                if (token != null) {
                    requestBuilder.addHeader("Authorization", "Bearer $token")
                }
                
                requestBuilder
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .method(original.method, original.body)
                
                chain.proceed(requestBuilder.build())
            }
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.virtualworld.com/v1/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    
    @Provides
    @Singleton
    fun provideVirtualWorldApi(retrofit: Retrofit): VirtualWorldApi =
        retrofit.create(VirtualWorldApi::class.java)
    
    @Provides
    @Singleton
    fun provideVirtualWorldDatabase(
        @ApplicationContext context: Context
    ): VirtualWorldDatabase = Room.databaseBuilder(
        context,
        VirtualWorldDatabase::class.java,
        "virtualworld_database"
    )
        .fallbackToDestructiveMigration()
        .build()
    
    @Provides
    @Singleton
    fun provideUserDao(database: VirtualWorldDatabase): UserDao =
        database.userDao()
    
    @Provides
    @Singleton
    fun provideFriendDao(database: VirtualWorldDatabase): FriendDao =
        database.friendDao()
    
    @Provides
    @Singleton
    fun provideMessageDao(database: VirtualWorldDatabase): MessageDao =
        database.messageDao()
    
    @Provides
    @Singleton
    fun providePurchaseDao(database: VirtualWorldDatabase): PurchaseDao =
        database.purchaseDao()
}
