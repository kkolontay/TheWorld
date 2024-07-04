package com.kodeco.android.countryinfo.dao.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.kodeco.android.countryinfo.models.Country
import kotlinx.coroutines.flow.Flow

@Dao
interface CountriesDAO {
    @Transaction
    @Query( "SELECT * FROM countries")
    suspend fun getCountries(): List<Country>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCountries(country: List<Country>)

    @Transaction
    @Query("SELECT * FROM countries WHERE commonName = :name ")
     suspend fun getCountry( name: String): Country

    @Query("DELETE FROM countries")
    suspend fun deleteAllCountries()
}