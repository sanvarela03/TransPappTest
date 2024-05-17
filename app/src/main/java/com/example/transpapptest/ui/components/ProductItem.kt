package com.example.transpapptest.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.transpapptest.data.local.entities.ProductEntity

@Composable
fun ProductItem(
    product: ProductEntity,
    onAddToChoppingCart: (Int) -> Unit
) {
    var quantity by rememberSaveable { mutableStateOf(1) }
    val context = LocalContext.current


    OutlinedCard(
//        border = BorderStroke(0.5.dp, Color.Black),
        modifier = Modifier
            .wrapContentHeight()
            .width(400.dp)
//            .size(
//                width = 400.dp,
//                height = 150.dp
//            )

    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(text = product.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(12.dp))



            Text(
                text = product.description,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Precio : $ ${product.price}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "Unidades disponibles: ${product.unitsAvailable}",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {


                IconButton(onClick = {
                    if (quantity > 1) quantity--
                }) {
                    Icon(Icons.Filled.Remove, contentDescription = "Decrease quantity")
                }

                var text by remember { mutableStateOf("1") }

                OutlinedTextField(
                    value = quantity.toString(),
                    onValueChange = {
                        if (it.isNotEmpty()) {
                            quantity = it.toInt()
                        }
                    },
                    modifier = Modifier
                        .width(90.dp)
                        .height(60.dp)
                        .padding(1.dp)
                        .align(Alignment.CenterVertically),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    keyboardActions = KeyboardActions(onDone = { /* Do something here if needed */ }),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    label = {
                        Text(
                            text = "Cantidad",
                            maxLines = 1,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    },
                )

                IconButton(
                    onClick = {
                        if (quantity < product.unitsAvailable) quantity++
                    }
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Increase quantity")
                }
                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = { onAddToChoppingCart(quantity) }) {
                    Icon(Icons.Filled.AddShoppingCart, contentDescription = "Add to shopping cart")
                }
            }
        }
    }
}
