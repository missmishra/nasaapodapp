package com.tech.nasaapodapp.nasa_feature.presentation.composable

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoadingItem(modifier: Modifier){
    CircularProgressIndicator(modifier = modifier)
}