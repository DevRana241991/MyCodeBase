package com.example.fitpeotasksample.data.network

import com.example.fitpeotasksample.data.model.GetServerData
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun getUser(): Flow<ArrayList<GetServerData>>
}