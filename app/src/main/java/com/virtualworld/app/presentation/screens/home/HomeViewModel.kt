package com.virtualworld.app.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.app.data.model.User
import com.virtualworld.app.data.model.VirtualWorld
import com.virtualworld.app.data.repository.AuthRepository
import com.virtualworld.app.data.repository.MessageRepository
import com.virtualworld.app.data.repository.WorldRepository
import com.virtualworld.app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeState(
    val currentUser: User? = null,
    val featuredWorlds: List<VirtualWorld> = emptyList(),
    val unreadMessageCount: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val worldRepository: WorldRepository,
    private val messageRepository: MessageRepository
) : ViewModel() {
    
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()
    
    init {
        loadData()
    }
    
    private fun loadData() {
        viewModelScope.launch {
            authRepository.getCurrentUser().collect { user ->
                _state.value = _state.value.copy(currentUser = user)
                
                user?.let {
                    loadFeaturedWorlds()
                    observeUnreadMessages(it.id)
                }
            }
        }
    }
    
    private fun loadFeaturedWorlds() {
        viewModelScope.launch {
            worldRepository.getFeaturedWorlds().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            featuredWorlds = result.data ?: emptyList(),
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
    
    private fun observeUnreadMessages(userId: String) {
        viewModelScope.launch {
            messageRepository.getUnreadCount(userId).collect { count ->
                _state.value = _state.value.copy(unreadMessageCount = count)
            }
        }
    }
}
