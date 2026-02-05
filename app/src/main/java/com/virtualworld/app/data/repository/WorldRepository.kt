package com.virtualworld.app.data.repository

import com.virtualworld.app.data.api.VirtualWorldApi
import com.virtualworld.app.data.model.VirtualWorld
import com.virtualworld.app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorldRepository @Inject constructor(
    private val api: VirtualWorldApi
) {
    
    suspend fun getWorlds(page: Int = 0, filter: String? = null): Flow<Resource<List<VirtualWorld>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getWorlds(page = page, filter = filter)
            if (response.isSuccessful && response.body() != null) {
                emit(Resource.Success(response.body()!!))
            } else {
                emit(Resource.Error("Failed to load worlds: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }
    
    suspend fun getWorld(worldId: String): Flow<Resource<VirtualWorld>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getWorld(worldId)
            if (response.isSuccessful && response.body() != null) {
                emit(Resource.Success(response.body()!!))
            } else {
                emit(Resource.Error("Failed to load world: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }
    
    suspend fun getFeaturedWorlds(): Flow<Resource<List<VirtualWorld>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getFeaturedWorlds()
            if (response.isSuccessful && response.body() != null) {
                emit(Resource.Success(response.body()!!))
            } else {
                emit(Resource.Error("Failed to load featured worlds: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }
    
    suspend fun joinWorld(worldId: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.joinWorld(worldId)
            if (response.isSuccessful) {
                emit(Resource.Success(Unit))
            } else {
                emit(Resource.Error("Failed to join world: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }
    
    suspend fun leaveWorld(worldId: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.leaveWorld(worldId)
            if (response.isSuccessful) {
                emit(Resource.Success(Unit))
            } else {
                emit(Resource.Error("Failed to leave world: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }
}
