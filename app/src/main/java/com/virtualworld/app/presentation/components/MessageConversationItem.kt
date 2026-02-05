package com.virtualworld.app.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.virtualworld.app.data.model.Message
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MessageConversationItem(
    conversation: Message,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dateFormat = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault())
    
    ListItem(
        headlineContent = { Text(conversation.senderName) },
        supportingContent = { Text(conversation.content, maxLines = 1) },
        trailingContent = { 
            Text(
                dateFormat.format(Date(conversation.timestamp)),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        },
        leadingContent = {
            AsyncImage(
                model = conversation.senderAvatarUrl ?: "",
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        },
        modifier = modifier.clickable(onClick = onClick)
    )
    Divider()
}
