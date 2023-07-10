package com.example.fitpeotasksample.api

import com.example.fitpeotasksample.JsonProvider
import com.example.fitpeotasksample.data.model.GetServerData
import com.example.fitpeotasksample.data.network.ApiServices
import java.lang.Exception
import javax.inject.Inject

class FakeApiService @Inject constructor() : ApiServices {

    var failUserApi: Boolean = false
    var wrongResponse: Boolean = false

    override suspend fun getUser(): ArrayList<GetServerData> {
        if (failUserApi) throw Exception("Api failed")
        val fakeResponse: ArrayList<GetServerData> = JsonProvider.objectFromJsonFileWithType(USER_JSON)
        val emptyList=ArrayList<GetServerData>()

        if (wrongResponse) return emptyList


        return fakeResponse
    }

    companion object {
        private const val USER_JSON = "/json/user.json"
    }
}