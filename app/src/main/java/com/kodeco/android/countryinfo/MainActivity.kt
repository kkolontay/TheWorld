package com.kodeco.android.countryinfo

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.datastore.preferences.preferencesDataStore
import com.kodeco.android.countryinfo.dao.database.CountriesDatabase
import com.kodeco.android.countryinfo.dao.database.repository.CountryDAORepository
import com.kodeco.android.countryinfo.dao.database.repository.CountryDAORepositoryImpl
import com.kodeco.android.countryinfo.network.CountryService
import com.kodeco.android.countryinfo.network.adapters.CountryAdapter
import com.kodeco.android.countryinfo.repositories.CountryRepository
import com.kodeco.android.countryinfo.repositories.CountryRepositoryImpl
import com.kodeco.android.countryinfo.ui.nav.CountryInfoNavHost
import com.kodeco.android.countryinfo.ui.theme.MyApplicationTheme
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


private const val USER_PREFERENCES = "theWorldPreferences"
class MainActivity : ComponentActivity() {
 private val Context.dataStore by preferencesDataStore(name = USER_PREFERENCES)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val moshi = Moshi.Builder()
            .add(CountryAdapter())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        val database: CountriesDatabase by lazy {
            CountriesDatabase.buildDatabase(
                context = this@MainActivity
            )
        }

        val daoRepository: CountryDAORepository by lazy {
            CountryDAORepositoryImpl(database.countiesDAO())
        }

        val service: CountryService = retrofit.create(CountryService::class.java)
        val repository: CountryRepository = CountryRepositoryImpl(service)

        setContent {
            MyApplicationTheme {
                CountryInfoNavHost(repository = repository)
            }
        }
    }
}
