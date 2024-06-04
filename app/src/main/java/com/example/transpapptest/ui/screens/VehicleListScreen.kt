package com.example.transpapptest.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.transpapptest.data.local.entities.VehicleEntity
import com.example.transpapptest.ui.components.VehicleItem
import com.example.transpapptest.ui.events.AddressListEvent
import com.example.transpapptest.ui.events.VehicleListEvent
import com.example.transpapptest.ui.navigation.Screen
import com.example.transpapptest.ui.viewmodels.VehicleListViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun VehicleListScreen(
    viewModel: VehicleListViewModel = hiltViewModel(),
    navigateTo: (String) -> Unit
) {
    val state = viewModel.state
    val vehicles: List<VehicleEntity> = state.vehicleList
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )

    Column(
        modifier = Modifier.padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mis vehiculos",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(15.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(15.dp))
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(VehicleListEvent.Refresh)
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(vehicles) { vehicle ->
                    VehicleItem(
                        vehicle = vehicle,
                        onEdit = {

                        },
                        onDelete = {

                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
    MyFloatingButton(navigateTo)
}

@Composable
private fun MyFloatingButton(navigateTo: (String) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        ExtendedFloatingActionButton(
            text = { Text(text = "Agregar vehiculo") },
            icon = { Icon(Icons.Filled.Add, "Extended floating action button.") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color.LightGray,
            onClick = { navigateTo(Screen.AddEditVehicleScreen.route) },
        )
    }
}