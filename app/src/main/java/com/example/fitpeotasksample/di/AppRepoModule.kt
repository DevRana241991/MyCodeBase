package com.example.fitpeotasksample.di

import com.example.fitpeotasksample.data.network.ApiRepository
import com.example.fitpeotasksample.data.network.ApiRepositoryImpl
import com.example.fitpeotasksample.data.network.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class AppRepoModule {
    @Provides
    fun providesApiRepository(apiServices: ApiServices): ApiRepository =
        ApiRepositoryImpl(apiServices)
}

@InstallIn(SingletonComponent::class)
@Module
class ApiServiceModule {
    @Provides
    fun providesApiServices(retrofit: Retrofit): ApiServices = ApiServices.create(retrofit)
}