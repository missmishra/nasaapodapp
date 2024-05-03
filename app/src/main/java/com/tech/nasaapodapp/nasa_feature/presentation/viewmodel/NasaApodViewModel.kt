package com.tech.nasaapodapp.nasa_feature.presentation.viewmodel

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.nasaapodapp.nasa_feature.domain.usecase.GetNasaApodDataUsecase
import com.tech.nasaapodapp.nasa_feature.presentation.util.NasaApodState
import com.tech.nasaapodapp.core.util.Resource
import com.tech.nasaapodapp.nasa_feature.data.remote.model.NasaApodDataItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class NasaApodViewModel @Inject constructor(
    private val getNasaApodDataUsecase: GetNasaApodDataUsecase
) : ViewModel() {
    private val _state = MutableStateFlow<NasaApodState>(NasaApodState.Loading)
    val state: StateFlow<NasaApodState> = _state

     val _apodData = MutableLiveData<NasaApodDataItem>()
    val apodData : LiveData<NasaApodDataItem> = _apodData

    init {
        getApodData(formatMillisToDate())
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatMillisToDate(): String {
        /*
        *   Fetching 1 day before data as the api return only month wise as 1-04-2024
        *   will have only one data so to show list of data passing one day prior date
        */
        val currentDateTime = LocalDateTime.now().minus(30, ChronoUnit.DAYS)
        // Define the format
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        // Format the current date and time
        return currentDateTime.format(formatter)
    }

    fun getApodData(date: String) {
        viewModelScope.launch {
            getNasaApodDataUsecase(date).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = NasaApodState.Success(result.data)
                    }

                    is Resource.Error -> {
                        _state.value = NasaApodState.Error(
                            result.message ?: "An unexpected error occured"
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = NasaApodState.Loading
                    }
                }
            }
        }
    }

    fun setNavigationData(data: NasaApodDataItem){
        _apodData.value = data
    }


}