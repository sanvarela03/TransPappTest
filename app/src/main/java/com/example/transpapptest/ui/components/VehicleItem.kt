package com.example.transpapptest.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.transpapptest.data.local.entities.VehicleEntity

@Composable
fun VehicleItem(
    vehicle: VehicleEntity,
    onEdit: (Long) -> Unit,
    onDelete: (Long) -> Unit,
) {

    Column(
        modifier = Modifier
            .padding(10.dp)

    ) {
        OutlinedCard(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(15.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Outlined.DirectionsCar, contentDescription = "")
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = "VIN: ${vehicle.vin}",
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.Light
                    )
                    MyDropdownMenu(
                        onDeleteClick = { onEdit(vehicle.id) },
                        onEditClick = { onDelete(vehicle.id) }
                    )

                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${vehicle.brand} ${vehicle.model}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "${vehicle.year}",
                        fontWeight = FontWeight.Light,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row {
                    Text(
                        text = "${vehicle.fuelType.uppercase()}",
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "${vehicle.fuelConsumption} ${vehicle.fuelConsumptionUnit}",
//                    modifier = Modifier.weight(1f)
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun VehicleItemPreview() {
    VehicleItem(
        vehicle = VehicleEntity(
            id = -1L,
            brand = "Renault",
            model = "Duster",
            year = "2015",
            vin = "WBAEH73434B656853",
            fuelType = "gas",
            fuelConsumption = 10.1,
            fuelConsumptionUnit = "L/100km",
            cargoVolume = 1570.0,
            payload = 500.0,
            transporterId = -1L,
        ),
        onEdit = {},
        onDelete = {}
    )
}