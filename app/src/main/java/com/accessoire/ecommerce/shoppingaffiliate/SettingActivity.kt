package com.accessoire.ecommerce.shoppingaffiliate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@Composable
fun SettingActivity(hazeState: HazeState) {
    Column (
        modifier = Modifier
            .haze(
                hazeState,
                backgroundColor = MaterialTheme.colorScheme.background,
                tint = Color.Black.copy(alpha = .2f),
                blurRadius = 30.dp,
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "SettingActivity",
            modifier = Modifier.fillMaxSize(),

            )
    }
}