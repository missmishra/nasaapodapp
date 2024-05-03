package com.tech.nasaapodapp.nasa_feature.di

import com.squareup.leakcanary.core.BuildConfig
import com.tech.nasaapodapp.nasa_feature.data.remote.NasaApodAPI
import com.tech.nasaapodapp.nasa_feature.data.repository.NasaApodRepositoryImpl
import com.tech.nasaapodapp.nasa_feature.domain.repository.NasaApodRepository
import com.tech.nasaapodapp.core.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {
    @Provides
    @ViewModelScoped
    fun provideNasaApodRepository(api: NasaApodAPI): NasaApodRepository {
        return NasaApodRepositoryImpl(api)
    }

}