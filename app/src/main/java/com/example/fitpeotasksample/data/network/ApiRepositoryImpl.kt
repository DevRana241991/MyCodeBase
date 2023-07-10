package com.example.fitpeotasksample.data.network

import com.example.fitpeotasksample.data.model.GetServerData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiRepositoryImpl(private val services: ApiServices) : ApiRepository {

    override suspend fun getUser(): Flow<ArrayList<GetServerData>> = flow {
        emit(services.getUser())
    }
}