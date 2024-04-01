package com.tech.nasaapodapp.presentation

sealed class Screen(val route: String) {
    object NasaApodScreen: Screen("nasa_apod_screen")
}
