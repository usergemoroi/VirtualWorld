package com.virtualworld.app.presentation.screens.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.app.data.model.Friend
import com.virtualworld.app.data.repository.AuthRepository
import com.virtualworld.app.data.repository.FriendRepository
import com.virtualworld.app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FriendsState(
    val friends: List<Friend> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val friendRepository: FriendRepository
) : ViewModel() {
    
    private val _state = MutableStateFlow(FriendsState())
    val state: StateFlow<FriendsState> = _state.asStateFlow()
    
    init {
        loadFriends()
    }
    
    private fun loadFriends() {
        viewModelScope.launch {
            authRepository.getCurrentUser().collect { user ->
                user?.let {
                    friendRepository.getFriends(it.id).collect { result ->
                        when (result) {
                            is Resource.Loading -> {
                                _state.value = _state.value.copy(isLoading = true)
                            }
                            is Resource.Success -> {
                                _state.value = _state.value.copy(
                                    friends = result.data ?: emptyList(),
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
        }
    }
}
