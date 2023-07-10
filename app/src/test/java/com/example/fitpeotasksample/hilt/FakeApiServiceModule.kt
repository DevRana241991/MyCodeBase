package com.example.fitpeotasksample.hilt

import com.example.fitpeotasksample.api.FakeApiService
import com.example.fitpeotasksample.data.network.ApiServices
import com.example.fitpeotasksample.di.ApiServiceModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [ApiServiceModule::class])
abstract class FakeApiServiceModule {

    @Binds
    @Singleton
    abstract fun providesApiServices(fakeApiService: FakeApiService): ApiServices
}