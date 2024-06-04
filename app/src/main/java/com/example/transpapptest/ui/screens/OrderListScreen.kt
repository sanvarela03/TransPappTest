package com.example.transpapptest.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.transpapptest.data.local.entities.TransporterEntity
import com.example.transpapptest.ui.components.MyDialog
import com.example.transpapptest.ui.components.MyDialog2
import com.example.transpapptest.ui.components.OrderItem
import com.example.transpapptest.ui.events.HomeEvent
import com.example.transpapptest.ui.events.OrderListEvent
import com.example.transpapptest.ui.theme.MyGreen
import com.example.transpapptest.ui.theme.Tomato
import com.example.transpapptest.ui.viewmodels.HomeViewModel
import com.example.transpapptest.ui.viewmodels.OrderListViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderListScreen(
    viewModel: OrderListViewModel = hiltViewModel()
) {
    val transporterId = viewModel.userId.collectAsState(initial = -1L).value
//    val transporter = viewModel.getTransporter(transporterId).collectAsState(initial = null).value
    val transporter = viewModel.state.transporterInfoResponse
    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )


    if (transporter != null) {
        MyDialog2(
            tittle = "¿ Desea  ${if (transporter.available) "no recibir" else "recibir"} pedidos?",
            text = if (transporter.available) "Al confirmar no recibirá solicitudes de pedidos" else "Al confirmar recibirá notificaciones de pedidos",
            show = viewModel.showConfirmationDialog,
            onDismiss = { viewModel.showConfirmationDialog = false },
            onConfirm = {
                viewModel.onEvent(OrderListEvent.UpdateAvailabilityBtnClick(transporter.toTransporterEntity()))
                viewModel.showConfirmationDialog = false
            }
        )
    }

    MyDialog(
        tittle = "Disponibilidad",
        text = viewModel.msgResponse,
        show = viewModel.showResponseDialog,
        onDismiss = { viewModel.showResponseDialog = false },
        onConfirm = { viewModel.showResponseDialog = false }
    )

    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mis pedidos",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(15.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(15.dp))

        Text(text = transporter.toString())

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(OrderListEvent.Refresh)
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 30.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (state.orderList.isNotEmpty()) {
                    items(state.orderList) { order ->
                        OrderItem(order)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

            }
        }
    }

    if (transporter != null) {
        OrderNotificationFloatingButton(
            transporter.toTransporterEntity(),
            viewModel
        )
    }
}

@Composable
private fun OrderNotificationFloatingButton(
    transporter: TransporterEntity?,
    viewModel: OrderListViewModel
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        ExtendedFloatingActionButton(
            text = { Text(text = "Recibir pedidos") },
            icon = {
                Icon(
                    Icons.Filled.PowerSettingsNew,
                    "Extended floating action button.",
                    tint = if (transporter != null) {
                        if (transporter.available) {
                            MyGreen
                        } else {
                            Tomato
                        }
                    } else {
                        Color.LightGray
                    }
                )
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(25.dp),
            containerColor = Color.LightGray,
            onClick = {
                viewModel.showConfirmationDialog = true
            },
        )
    }
}
