package com.virtualworld.app.presentation.screens.messages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.app.data.model.Message
import com.virtualworld.app.data.model.MessageType
import com.virtualworld.app.data.repository.AuthRepository
import com.virtualworld.app.data.repository.MessageRepository
import com.virtualworld.app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

data class ConversationState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ConversationViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val messageRepository: MessageRepository
) : ViewModel() {
    
    private val _state = MutableStateFlow(ConversationState())
    val state: StateFlow<ConversationState> = _state.asStateFlow()
    
    private var currentUserId: String? = null
    
    init {
        viewModelScope.launch {
            authRepository.getCurrentUser().collect { user ->
                currentUserId = user?.id
            }
        }
    }
    
    fun loadConversation(otherUserId: String) {
        viewModelScope.launch {
            currentUserId?.let { userId ->
                messageRepository.getConversation(userId, otherUserId).collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _state.value = _state.value.copy(isLoading = true)
                        }
                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                messages = result.data ?: emptyList(),
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
    
    fun sendMessage(recipientId: String, content: String) {
        viewModelScope.launch {
            currentUserId?.let { senderId ->
                val message = Message(
                    id = UUID.randomUUID().toString(),
                    senderId = senderId,
                    senderName = "You",
                    recipientId = recipientId,
                    content = content,
                    messageType = MessageType.TEXT,
                    timestamp = System.currentTimeMillis()
                )
                
                messageRepository.sendMessage(message).collect { result ->
                    when (result) {
                        is Resource.Error -> {
                            _state.value = _state.value.copy(error = result.message)
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}
