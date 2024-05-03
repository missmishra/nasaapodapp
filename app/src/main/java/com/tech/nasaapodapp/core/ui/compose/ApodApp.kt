package com.tech.nasaapodapp.core.ui.compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tech.nasaapodapp.core.navigation.NavDestination
import com.tech.nasaapodapp.nasa_feature.presentation.screen.NasaApodItemScreen
import com.tech.nasaapodapp.nasa_feature.presentation.screen.NasaApodScreen
import com.tech.nasaapodapp.nasa_feature.presentation.viewmodel.NasaApodViewModel
import com.tech.nasaapodapp.nasa_feature.presentation.viewmodel.SharedViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ApodApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    topAppBarTitle: (State<NavBackStackEntry?>) -> String,
    sharedViewModel: SharedViewModel
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBar(
                appBarTitle = topAppBarTitle(navController.currentBackStackEntryAsState()),
                isNavigateBack = navController.previousBackStackEntry != null,
                navigateBack = { navController.popBackStack() }
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = NavDestination.APODLIST,
            modifier = Modifier.padding(it)
        ) {
            composable(NavDestination.APODLIST) {
                val nasaApodViewModel = hiltViewModel<NasaApodViewModel>()
                NasaApodScreen(navController, nasaApodViewModel, sharedViewModel)
            }
            composable(NavDestination.APODDETAIL) {
                NasaApodItemScreen(sharedViewModel)
            }
        }
    }

}