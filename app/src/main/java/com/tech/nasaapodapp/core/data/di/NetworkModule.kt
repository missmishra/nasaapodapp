package com.tech.nasaapodapp.core.data.di

import com.tech.nasaapodapp.nasa_feature.data.remote.NasaApodAPI
import com.tech.nasaapodapp.core.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()



    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constant.BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideNasaApodAPIService(retrofit: Retrofit): NasaApodAPI =
        retrofit.create(NasaApodAPI::class.java)
}