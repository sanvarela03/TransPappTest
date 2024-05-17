package com.example.transpapptest.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.transpapptest.ui.components.ProducerItem
import com.example.transpapptest.ui.events.ProducerDetailEvent
import com.example.transpapptest.ui.events.ProducerSearchEvent
import com.example.transpapptest.ui.viewmodels.ProducerDetailViewModel
import com.example.transpapptest.ui.components.ProductItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ProducerDetailScreen(
    viewModel: ProducerDetailViewModel = hiltViewModel()
) {
    val productList = viewModel.state.productsList
    val producer = viewModel.state.producer
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )

    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Productos Disponibles",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(15.dp))

        if (producer != null) {
            Text(text = producer.name)
        }
        if (producer != null) {
            Text(text = producer.lastName)
        }
        if (producer != null) {
            Text(text = producer.phone)
        }
        if (producer != null) {
            Text(text = producer.email)
        }

        HorizontalDivider()
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(ProducerDetailEvent.Refresh)
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 30.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (productList.isNotEmpty()) {
                    items(productList) { productEntity ->
                        ProductItem(
                            productEntity,
                            onAddToChoppingCart = {
                                viewModel.onEvent(
                                    ProducerDetailEvent.AddProductToCart(
                                        productEntity.toShoppingCartEntity(
                                            it
                                        )
                                    )
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }

    }
}