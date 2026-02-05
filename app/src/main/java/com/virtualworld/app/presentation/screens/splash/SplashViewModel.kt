package com.virtualworld.app.presentation.screens.splash

import androidx.lifecycle.ViewModel
import com.virtualworld.app.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    
    suspend fun isLoggedIn(): Boolean {
        return authRepository.isLoggedIn()
    }
}
