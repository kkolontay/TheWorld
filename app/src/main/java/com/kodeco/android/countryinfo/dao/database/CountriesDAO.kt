package com.kodeco.android.countryinfo.dao.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kodeco.android.countryinfo.models.Country

@Dao
interface CountriesDAO {
    @Query( "SELECT * FROM countries")
    fun getCountries(): List<Country>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCountry(country: Country)

    @Query("SELECT * FROM countries WHERE commonName = :name ")
    fun getCountry( name: String): Country
}