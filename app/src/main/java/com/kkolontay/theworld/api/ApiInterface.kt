package com.kkolontay.theworld.api

import com.kkolontay.theworld.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
        @GET("all")
        suspend fun getAllCountries(): Response<List<Country>>
}

sealed class WorldResponse {
        class Success(val list: List<Country>): WorldResponse()
        object Loading: WorldResponse()
        object Error: WorldResponse()

}