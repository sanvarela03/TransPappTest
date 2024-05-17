package com.example.transpapptest.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.transpapptest.data.local.dto.NavigationItem


@Composable
fun NavigationDrawerBody(
    navigationItems: List<NavigationItem>,
    navigateTo: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(navigationItems) { navItem: NavigationItem ->
            NavigationItemRow(item = navItem, navigateTo = { navigateTo(navItem.itemId) })
        }
    }
}