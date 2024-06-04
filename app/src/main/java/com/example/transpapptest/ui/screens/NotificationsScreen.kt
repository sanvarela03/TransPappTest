package com.example.transpapptest.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.transpapptest.data.local.entities.NotificationEntity
import com.example.transpapptest.ui.events.NotificationsEvent
import com.example.transpapptest.ui.viewmodels.NotificationViewModel

@Composable
fun NotificationsScreen(
    notificationViewModel: NotificationViewModel = hiltViewModel()
) {
    val notifications =
        notificationViewModel.notifications.collectAsState(initial = emptyList()).value

    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(notifications) { notification: NotificationEntity ->
                OutlinedCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = notification.title,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(4f)
                            )
                            IconButton(
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    notificationViewModel.onEvent(
                                        NotificationsEvent.DeleteBtnClick(
                                            notification.id ?: -1
                                        )
                                    )
                                }
                            ) {
                                Icon(Icons.Filled.Delete, contentDescription = "")
                            }
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = notification.body)
                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                modifier = Modifier.weight(1f),
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Light,
                                text = notification.createdAt,
                                textAlign = TextAlign.Right
                            )
                        }
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

    }
}