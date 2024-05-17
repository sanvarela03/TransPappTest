package com.example.transpapptest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.transpapptest.R
import com.example.transpapptest.ui.theme.AccentColor
import com.example.transpapptest.ui.theme.Primary
import com.example.transpapptest.ui.theme.Secondary

@Composable
fun NavigationDrawerHeader(
    value: String?
) {
    Box(
        modifier = Modifier
            .background(Brush.horizontalGradient(listOf(Primary, Secondary)))
            .fillMaxWidth()
            .height(180.dp)
            .padding(32.dp)
    ) {
        NavigationDrawerText(
            tittle = value ?: stringResource(id = R.string.navigation_header),
            textUnit = 28.sp,
            color = AccentColor
        )
    }
}