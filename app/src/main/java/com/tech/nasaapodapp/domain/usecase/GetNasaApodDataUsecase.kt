package com.tech.nasaapodapp.domain.usecase

import com.tech.nasaapodapp.data.remote.dto.NasaApodData
import com.tech.nasaapodapp.data.remote.dto.NasaApodDataItem
import com.tech.nasaapodapp.domain.repository.NasaApodRepository
import com.tech.nasaapodapp.util.Constant
import com.tech.nasaapodapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class GetNasaApodDataUsecase @Inject constructor(
    private val repository: NasaApodRepository
){
    operator fun invoke(date: String): Flow<Resource<List<NasaApodDataItem>>> = flow {
        try {
            emit(Resource.Loading())
            val nasaApodData = repository.getNasaApod(Constant.API_KEY,date)
            Timber.e("GetNasaApodDataUsecase: $nasaApodData")
            emit(Resource.Success(nasaApodData))
        } catch(e: HttpException) {
            Timber.e("GetNasaApodDataUsecase: error")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            Timber.e("GetNasaApodDataUsecase: IOException error")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))

        }
    }
}