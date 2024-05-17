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
import com.example.transpapptest.ui.components.ButtonComponent
import com.example.transpapptest.ui.components.HeadingTextComponent
import com.example.transpapptest.ui.components.MyNumberFieldComponent
import com.example.transpapptest.ui.components.MyPicker3
import com.example.transpapptest.ui.components.MyTextFieldComponentIcons
import com.example.transpapptest.ui.events.AddEditAddressEvent


@Composable
fun AddEditVehicleScreen() {
//    val state = viewModel.state
//
//    val cityList = state.cityList
//    val stateList = state.stateList
//    val countryList = state.countryList
//    val isCurrentAddressList = listOf("Si", "No")
//
//    val isAttemptingToSave = viewModel.isAttemptingToSave
//
//    dialog(viewModel, isAttemptingToSave, homeViewModel, navigateTo)
//
//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.White)
//            .padding(28.dp)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.White)
//                .verticalScroll(rememberScrollState()),
//            verticalArrangement = Arrangement.Center
//        ) {
//            HeadingTextComponent(value = "${if (isAttemptingToSave) "Agregar" else "Editar"} Vehículo")
//            Spacer(modifier = Modifier.height(40.dp))
//            MyTextFieldComponentIcons(
//                textValue = state.name,
//                labelValue = "Marca",
//                imageVector = Icons.Outlined.Create,
//                onTextSelected = {
//                    viewModel.onEvent(AddEditAddressEvent.NameChanged(it))
//                },
//                errorStatus = false
////                signInViewModel.state.usernameError
//            )
//            MyTextFieldComponentIcons(
//                textValue = state.instruction,
//                labelValue = "Modelo",
//                imageVector = Icons.Outlined.Description,
//                onTextSelected = {
//                    viewModel.onEvent(AddEditAddressEvent.InstructionChanged(it))
//                },
//                errorStatus = false
////                signInViewModel.state.usernameError
//            )
//
//            MyTextFieldComponentIcons(
//                textValue = state.street,
//                labelValue = "Año",
//                imageVector = Icons.Outlined.EditRoad,
//                onTextSelected = {
//                    viewModel.onEvent(AddEditAddressEvent.StreetChanged(it))
//                },
//                errorStatus = false
////                signInViewModel.state.usernameError
//            )
//
//            MyTextFieldComponentIcons(
//                textValue = state.street,
//                labelValue = "VIN",
//                imageVector = Icons.Outlined.EditRoad,
//                onTextSelected = {
//                    viewModel.onEvent(AddEditAddressEvent.StreetChanged(it))
//                },
//                errorStatus = false
////                signInViewModel.state.usernameError
//            )
//
//
//            MyPicker3(
//                textValue = state.city,
//                labelValue = "Tipo de combustible",
//                imageVector = Icons.Outlined.LocationCity,
//                items = cityList,
//                onClick = {
//                    viewModel.onEvent(AddEditAddressEvent.CityChanged(cityList[it]))
//                },
//                errorStatus = false
//            )
//
//            MyTextFieldComponentIcons(
//                textValue = state.street,
//                labelValue = "Consumo de combustible",
//                imageVector = Icons.Outlined.EditRoad,
//                onTextSelected = {
//                    viewModel.onEvent(AddEditAddressEvent.StreetChanged(it))
//                },
//                errorStatus = false
////                signInViewModel.state.usernameError
//            )
//
//            MyPicker3(
//                textValue = state.state,
//                labelValue = "Unidad del consumo de combustible",
//                imageVector = Icons.Outlined.LocationCity,
//                items = stateList,
//                onClick = {
//                    viewModel.onEvent(AddEditAddressEvent.StateChanged(stateList[it]))
//                },
//                errorStatus = false
//            )
//
//
//            MyNumberFieldComponent(
//                textValue = state.latitude.toString(),
//                labelValue = "Volumen de carga (L)",
//                imageVector = Icons.Outlined.Straighten,
//                onTextSelected = {
////                    signInViewModel.onEvent(SignInUIEvent.UsernameChanged(it))
//                    viewModel.onEvent(AddEditAddressEvent.LatitudeChanged(if (it.isNotBlank()) it else "0.0"))
//                },
//                errorStatus = false,
////                signInViewModel.state.usernameError
//            )
//
//            MyNumberFieldComponent(
//                textValue = state.longitude.toString(),
//                labelValue = "Carga maxima (Kg)",
//                imageVector = Icons.Outlined.Straighten,
//                onTextSelected = {
////                    signInViewModel.onEvent(SignInUIEvent.UsernameChanged(it))
//                    viewModel.onEvent(AddEditAddressEvent.LongitudeChanged(if (it.isNotBlank()) it else "0.0"))
//                },
//                errorStatus = false,
////                signInViewModel.state.usernameError
//            )
//
//            Spacer(modifier = Modifier.height(5.dp))
//
//
//            MyPicker3(
//                textValue = if (state.isCurrentAddress) "Si" else "No",
//                labelValue = "¿ Establecer como vehiculo actual?",
//                imageVector = Icons.Outlined.MyLocation,
//                items = isCurrentAddressList,
//                onClick = {
//                    viewModel.onEvent(
//                        AddEditAddressEvent.IsCurrentAddressChanged(
//                            isCurrentAddressList[it] == "Si"
//                        )
//                    )
//                },
//                errorStatus = false
//            )
//
//
//            Spacer(modifier = Modifier.height(40.dp))
//            ButtonComponent(
//                value = "Guardar Vehiculo",
//                isEnabled = true
//            ) {
//                Log.d("SignInScreen", "Sign In BTN CLICK!")
//                viewModel.onEvent(AddEditAddressEvent.SaveBtnClick)
//            }
//        }
//    }
}