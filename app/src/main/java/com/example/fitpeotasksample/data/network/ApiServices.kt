package com.example.fitpeotasksample.data.network

import com.example.fitpeotasksample.data.model.GetServerData
import retrofit2.Retrofit
import retrofit2.http.GET

interface ApiServices {

    @GET("photos")
    suspend fun getUser(): ArrayList<GetServerData>

    companion object Factory {
        fun create(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)
    }
}