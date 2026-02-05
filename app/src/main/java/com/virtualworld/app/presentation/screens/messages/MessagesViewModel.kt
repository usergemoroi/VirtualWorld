package com.virtualworld.app.presentation.screens.messages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.app.data.model.Message
import com.virtualworld.app.data.repository.AuthRepository
import com.virtualworld.app.data.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MessagesState(
    val conversations: List<Message> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val messageRepository: MessageRepository
) : ViewModel() {
    
    private val _state = MutableStateFlow(MessagesState())
    val state: StateFlow<MessagesState> = _state.asStateFlow()
    
    init {
        loadMessages()
    }
    
    private fun loadMessages() {
        viewModelScope.launch {
            authRepository.getCurrentUser().collect { user ->
                user?.let {
                    messageRepository.getMessages(it.id).collect { messages ->
                        val uniqueConversations = messages
                            .distinctBy { msg -> 
                                if (msg.senderId == it.id) msg.recipientId else msg.senderId 
                            }
                        _state.value = _state.value.copy(
                            conversations = uniqueConversations,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}
