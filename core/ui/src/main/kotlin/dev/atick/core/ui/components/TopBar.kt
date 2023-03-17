package dev.atick.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.atick.core.ui.R

@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Row(
        modifier = modifier.then(Modifier.fillMaxHeight()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                )
                .width(8.dp)
                .height(32.dp)
                .background(color)
                .padding(end = 16.dp)
        )
        Image(

            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "logo"
        )
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    onSearchClick: (() -> Unit)? = null,
    onRefreshClick: (() -> Unit)? = null,
    onMenuClick: (() -> Unit)? = null,
    onExitClick: (() -> Unit)? = null,
    onLogoutClick: (() -> Unit)? = null
) {
    return Card(
        modifier = modifier
            .then(Modifier.fillMaxWidth()),
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 16.dp,
            bottomEnd = 16.dp
        ),
//        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TitleText()

            Row {
                onSearchClick?.let { onSearchClick ->
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                }

                onRefreshClick?.let { onRefreshClick ->
                    IconButton(onClick = onRefreshClick) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh"
                        )
                    }
                }

                onMenuClick?.let { onMenuClick ->
                    IconButton(onClick = onMenuClick) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Menu"
                        )
                    }
                }

                onLogoutClick?.let { onLogoutClick ->
                    IconButton(onClick = onLogoutClick) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "Logout"
                        )
                    }
                }

                onExitClick?.let { onExitClick ->
                    IconButton(onClick = onExitClick) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Exit"
                        )
                    }
                }
            }
        }
    }
}

