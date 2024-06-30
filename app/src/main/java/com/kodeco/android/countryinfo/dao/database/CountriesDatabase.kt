package com.kodeco.android.countryinfo.dao.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kodeco.android.countryinfo.models.Country

const val DATABASE_VERSION = 1

@Database(
    entities = [Country::class],
    version = DATABASE_VERSION
)
abstract class CountriesDatabase: RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "Countries"

        fun buildDatabase(context: Context): CountriesDatabase {
            return Room.databaseBuilder(context,
                CountriesDatabase::class.java,
                DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
        }
    }
}