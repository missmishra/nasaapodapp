package com.tech.nasaapodapp.nasa_feature.presentation.composable

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ErrorItem(errorMessage: String, modifier: Modifier) {
    Text(
        text = errorMessage,
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.SemiBold,
        fontSize = 25.sp,
        modifier = modifier
    )
}