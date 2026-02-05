package com.virtualworld.app.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.virtualworld.app.data.model.Friend

@Composable
fun FriendItem(
    friend: Friend,
    onFriendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineContent = { Text(friend.friendDisplayName) },
        supportingContent = { 
            Text(
                if (friend.isOnline) "Online" else "Offline",
                color = if (friend.isOnline) 
                    MaterialTheme.colorScheme.primary 
                else 
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        },
        leadingContent = {
            AsyncImage(
                model = friend.friendAvatarUrl ?: "",
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        },
        modifier = modifier.clickable(onClick = onFriendClick)
    )
    Divider()
}
