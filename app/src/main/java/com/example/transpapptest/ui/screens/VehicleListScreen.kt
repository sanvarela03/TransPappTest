package com.example.transpapptest.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.transpapptest.data.local.entities.VehicleEntity
import com.example.transpapptest.ui.components.VehicleItem
import com.example.transpapptest.ui.viewmodels.VehicleListViewModel

@Composable
fun VehicleListScreen(
    viewModel: VehicleListViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val vehicles: List<VehicleEntity> = state.vehicleList

    LazyColumn {
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