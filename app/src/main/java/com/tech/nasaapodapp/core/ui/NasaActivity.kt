package com.tech.nasaapodapp.core.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.tech.nasaapodapp.core.navigation.NavDestination
import com.tech.nasaapodapp.core.ui.compose.ApodApp
import com.tech.nasaapodapp.nasa_feature.presentation.theme.NasaApodAppTheme
import com.tech.nasaapodapp.nasa_feature.presentation.viewmodel.NasaApodViewModel
import com.tech.nasaapodapp.nasa_feature.presentation.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class NasaActivity : ComponentActivity() {
    private lateinit var sharedViewModel: SharedViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NasaApodAppTheme {
                sharedViewModel = remember {
                   SharedViewModel()
                }

                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.tertiary
                ) {
                    ApodApp(topAppBarTitle = ::getAppBarTitle, sharedViewModel = sharedViewModel)
                }
            }
        }
    }


    private fun getAppBarTitle(navBackStackEntry: State<NavBackStackEntry?>): String = when (
        navBackStackEntry.value?.destination?.route
    ) {
        NavDestination.APODLIST -> NavDestination.LIST_TITLE

        NavDestination.APODDETAIL -> sharedViewModel.nasaApodItem.value?.title
            ?: NavDestination.DETAIL_TITLE

        else -> NavDestination.LIST_TITLE

    }
}