package com.tech.nasaapodapp.presentation.component

import com.tech.nasaapodapp.data.remote.dto.NasaApodData
import com.tech.nasaapodapp.data.remote.dto.NasaApodDataItem

data class NasaApodState(
    val isLoading: Boolean = false,
    val apod: List<NasaApodDataItem>? = null,
    val error: String = ""
)
