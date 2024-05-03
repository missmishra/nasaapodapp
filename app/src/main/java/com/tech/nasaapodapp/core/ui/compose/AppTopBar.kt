package com.tech.nasaapodapp.core.ui.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    appBarTitle: String,
    isNavigateBack: Boolean,
    navigateBack: () -> Unit
) {
    TopAppBar(title = {
        Text(text = appBarTitle)
    }, navigationIcon = {
        if(isNavigateBack) {
            IconButton(onClick = { navigateBack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back press",
                )
            }
        }
    }
    )
}