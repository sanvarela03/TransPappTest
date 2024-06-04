package com.example.transpapptest.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.EditRoad
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material.icons.outlined.Straighten
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.transpapptest.ui.components.ButtonComponent
import com.example.transpapptest.ui.components.HeadingTextComponent
import com.example.transpapptest.ui.components.MyDialog
import com.example.transpapptest.ui.components.MyNumberFieldComponent
import com.example.transpapptest.ui.components.MyPicker3
import com.example.transpapptest.ui.components.MyTextFieldComponentIcons
import com.example.transpapptest.ui.events.AddEditAddressEvent
import com.example.transpapptest.ui.events.AddEditVehicleEvent
import com.example.transpapptest.ui.events.HomeEvent
import com.example.transpapptest.ui.navigation.graphs.Graph
import com.example.transpapptest.ui.viewmodels.AddEditVehicleViewModel


@Composable
fun AddEditVehicleScreen(
    viewModel: AddEditVehicleViewModel = hiltViewModel(),
    navigateTo: (String) -> Unit,
) {
    val state = viewModel.state

    val isCurrentVehicleList = listOf("Si", "No")

    val isAttemptingToSave = viewModel.isAttemptingToSave

    //dialog(viewModel, isAttemptingToSave, homeViewModel, navigateTo)

    val msg = viewModel.messageResponse

    MyDialog(
        tittle = "Vehiculo ${if (isAttemptingToSave) "guardado" else "actualizado"} exitosamente",
        text = msg,
        onConfirmTxt = "OK",
        show = viewModel.showDialog,
        onDismiss = { viewModel.showDialog = false },
        onConfirm = {
            viewModel.showDialog = false
            navigateTo(Graph.HOME)
        }
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
            HeadingTextComponent(value = "${if (isAttemptingToSave) "Agregar" else "Editar"} Vehículo")
            Spacer(modifier = Modifier.height(40.dp))

            MyPicker3(
                textValue = state.brand,
                labelValue = "Marca",
                imageVector = Icons.Outlined.Create,
                items = viewModel.brandList,
                onClick = {
                    viewModel.onEvent(AddEditVehicleEvent.BrandChanged(viewModel.brandList[it]))
                },
                errorStatus = false
            )
            MyTextFieldComponentIcons(
                textValue = state.model,
                labelValue = "Modelo",
                imageVector = Icons.Outlined.Create,
                onTextSelected = {
                    viewModel.onEvent(AddEditVehicleEvent.ModelChanged(it))
                },
                errorStatus = false
//                signInViewModel.state.usernameError
            )

            MyPicker3(
                textValue = state.year,
                labelValue = "Año",
                imageVector = Icons.Outlined.Create,
                items = viewModel.yearList,
                onClick = {
                    viewModel.onEvent(AddEditVehicleEvent.YearChanged(viewModel.yearList[it]))
                },
                errorStatus = false
            )
            MyTextFieldComponentIcons(
                textValue = state.vin,
                labelValue = "VIN",
                imageVector = Icons.Outlined.Create,
                onTextSelected = {
                    viewModel.onEvent(AddEditVehicleEvent.VinChanged(it))
                },
                errorStatus = false
//                signInViewModel.state.usernameError
            )

            MyTextFieldComponentIcons(
                textValue = state.plate,
                labelValue = "Placa",
                imageVector = Icons.Outlined.Create,
                onTextSelected = {
                    viewModel.onEvent(AddEditVehicleEvent.PlateChanged(it))
                },
                errorStatus = false
//                signInViewModel.state.usernameError
            )


            MyPicker3(
                textValue = state.fuelType,
                labelValue = "Tipo de combustible",
                imageVector = Icons.Outlined.Create,
                items = viewModel.fuelTypeList.map { it.uppercase() },
                onClick = {
                    viewModel.onEvent(AddEditVehicleEvent.FuelTypeChanged(viewModel.fuelTypeList.map { it.uppercase() }[it]))
                },
                errorStatus = false
            )

            MyTextFieldComponentIcons(
                textValue = state.fuelConsumption,
                labelValue = "Consumo de combustible",
                imageVector = Icons.Outlined.Create,
                onTextSelected = {
                    viewModel.onEvent(AddEditVehicleEvent.FuelConsumptionChanged(it))
                },
                errorStatus = false
//                signInViewModel.state.usernameError
            )
            MyPicker3(
                textValue = state.fuelConsumptionUnit,
                labelValue = "Unidad del consumo de combustible",
                imageVector = Icons.Outlined.Create,
                items = viewModel.fuelConsumptionUnitList,
                onClick = {
                    viewModel.onEvent(AddEditVehicleEvent.FuelConsumptionUnitChanged(viewModel.fuelConsumptionUnitList[it]))
                },
                errorStatus = false
            )

            MyTextFieldComponentIcons(
                textValue = state.cargoVolume,
                labelValue = "Volumen de carga",
                imageVector = Icons.Outlined.Create,
                onTextSelected = {
                    viewModel.onEvent(AddEditVehicleEvent.CargoVolumeChanged(it))
                },
                errorStatus = false
//                signInViewModel.state.usernameError
            )

            MyTextFieldComponentIcons(
                textValue = state.payload,
                labelValue = "Carga",
                imageVector = Icons.Outlined.Create,
                onTextSelected = {
                    viewModel.onEvent(AddEditVehicleEvent.PayloadChanged(it))
                },
                errorStatus = false
//                signInViewModel.state.usernameError
            )
            MyPicker3(
                textValue = if (state.isCurrentVehicle) "Si" else "No",
                labelValue = "¿ Establecer como vehiculo actual?",
                imageVector = Icons.Outlined.MyLocation,
                items = isCurrentVehicleList,
                onClick = {
                    viewModel.onEvent(
                        AddEditVehicleEvent.IsCurrentVehicleChanged(
                            isCurrentVehicleList[it] == "Si"
                        )
                    )
                },
                errorStatus = false
            )

            Spacer(modifier = Modifier.height(40.dp))
            ButtonComponent(
                value = "Guardar Vehiculo",
                isEnabled = true
            ) {
                Log.d("AddEditVehicleScreen", "SaveBtn CLICK!")
                viewModel.onEvent(AddEditVehicleEvent.SaveBtnClick)
            }
        }
    }
}