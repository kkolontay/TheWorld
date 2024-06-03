package com.kkolontay.theworld.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryName(
    @Json(name = "common") val common: String)

@JsonClass(generateAdapter = true)
data class CountryFlags(
    @Json(name = "png") val png: String)

@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = "name") val name: CountryName,
    @Json(name = "capital") val capital: List<String>?,
    @Json(name = "population") val population: Long,
    @Json(name = "area") val area: Double,
    @Json(name = "flags") val flags: CountryFlags)


