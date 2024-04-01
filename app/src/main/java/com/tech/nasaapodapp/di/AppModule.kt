package com.tech.nasaapodapp.di

import com.squareup.leakcanary.core.BuildConfig
import com.tech.nasaapodapp.data.remote.NasaApodAPI
import com.tech.nasaapodapp.data.repository.NasaApodRepositoryImpl
import com.tech.nasaapodapp.domain.repository.NasaApodRepository
import com.tech.nasaapodapp.util.Constant.BASE_URL
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
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()



    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideUserDetailsService(retrofit: Retrofit): NasaApodAPI =
        retrofit.create(NasaApodAPI::class.java)

    @Provides
    @Singleton
    fun provideCoinRepository(api: NasaApodAPI): NasaApodRepository {
        return NasaApodRepositoryImpl(api)
    }

}