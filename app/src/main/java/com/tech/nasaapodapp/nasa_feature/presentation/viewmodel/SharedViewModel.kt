package com.tech.nasaapodapp.nasa_feature.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tech.nasaapodapp.nasa_feature.data.remote.model.NasaApodDataItem

class SharedViewModel: ViewModel() {
    public var nasaApodItem = MutableLiveData<NasaApodDataItem>()
}