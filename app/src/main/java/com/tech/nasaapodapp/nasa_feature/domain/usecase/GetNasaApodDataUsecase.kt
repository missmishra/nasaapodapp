package com.tech.nasaapodapp.nasa_feature.domain.usecase

import com.tech.nasaapodapp.nasa_feature.domain.repository.NasaApodRepository
import com.tech.nasaapodapp.core.util.Constant
import com.tech.nasaapodapp.core.util.Resource
import com.tech.nasaapodapp.nasa_feature.data.remote.model.NasaApodDataItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNasaApodDataUsecase @Inject constructor(
    private val repository: NasaApodRepository
){
    operator fun invoke(date: String): Flow<Resource<List<NasaApodDataItem>>> = flow {
        try {
            emit(Resource.Loading())
            val nasaApodData = repository.getNasaApod(Constant.API_KEY,date)
            emit(Resource.Success(nasaApodData))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))

        }
    }
}