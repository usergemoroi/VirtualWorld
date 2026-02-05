package com.virtualworld.app.presentation.screens.world

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.app.data.model.VirtualWorld
import com.virtualworld.app.data.repository.WorldRepository
import com.virtualworld.app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WorldState(
    val world: VirtualWorld? = null,
    val isLoading: Boolean = false,
    val isJoining: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class WorldViewModel @Inject constructor(
    private val worldRepository: WorldRepository
) : ViewModel() {
    
    private val _state = MutableStateFlow(WorldState())
    val state: StateFlow<WorldState> = _state.asStateFlow()
    
    fun loadWorld(worldId: String) {
        viewModelScope.launch {
            worldRepository.getWorld(worldId).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            world = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
    
    fun joinWorld(worldId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isJoining = true)
            
            worldRepository.joinWorld(worldId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isJoining = false,
                            error = null
                        )
                        // Navigate to world view or show success
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isJoining = false,
                            error = result.message
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isJoining = true)
                    }
                }
            }
        }
    }
}
