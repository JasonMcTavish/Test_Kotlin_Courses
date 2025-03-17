package ru.test.data.api

import retrofit2.Response
import retrofit2.http.GET
import ru.test.data.model.DataModel

interface ApiService {
    @GET(
        "u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download"
    )
    suspend fun getData(): Response<DataModel>
}