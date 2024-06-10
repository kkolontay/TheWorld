package com.kkolontay.theworld.api

import com.kkolontay.theworld.model.Country
import retrofit2.http.GET

interface ApiInterface {
        @GET("all")
        suspend fun fetchCountryList(): List<Country>
}

sealed class WorldResponse {
        class Success(val list: List<Country>): WorldResponse()
        class Loading(): WorldResponse()
        class ErrorResponse(): WorldResponse()

}