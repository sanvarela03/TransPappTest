package com.example.transpapptest.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.transpapptest.data.local.entities.AddressEntity
import com.example.transpapptest.data.local.entities.ProducerEntity
import com.example.transpapptest.data.local.entities.relations.ProducerAndAddress

@Composable
fun ProducerItem(
    producerAndAddress: ProducerAndAddress,
    onClick: () -> Unit
) {
    val producer: ProducerEntity = producerAndAddress.producer
    val address: AddressEntity? = producerAndAddress.currentAddress
    OutlinedCard(
        modifier = Modifier
            .wrapContentHeight()
            .width(400.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = producer.name)
            Text(text = producer.lastName)
            Text(text = producer.email)
            Text(text = producer.phone)
            if (address != null) {
                Text(text = address.city)
            }

            IconButton(onClick = { onClick() }) {
                Icon(
                    imageVector = Icons.Filled.LocalShipping,
                    contentDescription = "Sign out"
                )
            }

        }
    }
}