package com.tech.nasaapodapp.presentation.component

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.nasaapodapp.domain.usecase.GetNasaApodDataUsecase
import com.tech.nasaapodapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class NasaApodViewModel @Inject constructor(
    private val getNasaApodDataUsecase: GetNasaApodDataUsecase
) : ViewModel() {
    private val _state = mutableStateOf(NasaApodState())
    val state: State<NasaApodState> = _state

    init {
        getApodData(formatMillisToDate())
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatMillisToDate(): String {
//        Fetching 1 day before data as the api return only month wise as 1-04-2024 will have only one data so to show list of data passing one day prior date
        val currentDateTime = LocalDateTime.now().minus(30, ChronoUnit.DAYS)
        // Define the format
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        // Format the current date and time
        return currentDateTime.format(formatter)
    }

    fun getApodData(date: String) {
        viewModelScope.launch {
            getNasaApodDataUsecase(date).collect { result ->
                Timber.e("NasaApodViewModel: ${result.data}")
                when (result) {
                    is Resource.Success -> {
                        _state.value = NasaApodState(apod = result.data)
                    }

                    is Resource.Error -> {
                        _state.value = NasaApodState(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = NasaApodState(isLoading = true)
                    }
                }
            }
        }

    }


}