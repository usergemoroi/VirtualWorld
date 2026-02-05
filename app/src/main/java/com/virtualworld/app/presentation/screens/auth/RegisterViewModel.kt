package com.virtualworld.app.presentation.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.app.data.repository.AuthRepository
import com.virtualworld.app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RegisterState(
    val isLoading: Boolean = false,
    val isRegisterSuccessful: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    
    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()
    
    fun register(
        email: String,
        password: String,
        confirmPassword: String,
        username: String,
        displayName: String
    ) {
        if (email.isBlank() || password.isBlank() || username.isBlank() || displayName.isBlank()) {
            _state.value = _state.value.copy(error = "Please fill in all fields")
            return
        }
        
        if (password != confirmPassword) {
            _state.value = _state.value.copy(error = "Passwords do not match")
            return
        }
        
        if (password.length < 8) {
            _state.value = _state.value.copy(error = "Password must be at least 8 characters")
            return
        }
        
        viewModelScope.launch {
            authRepository.register(email, password, username, displayName).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true, error = null)
                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            isRegisterSuccessful = true,
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
