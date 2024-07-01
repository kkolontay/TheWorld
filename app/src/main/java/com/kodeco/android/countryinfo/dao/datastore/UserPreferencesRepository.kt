package com.kodeco.android.countryinfo.dao.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>,
    context: Context
) {
    val userPreferences: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val rotateMode = preferences[PreferencesKeys.isRotateMode]?: false
            val localStore = preferences[PreferencesKeys.localStoreEnable]?: false
            val favoriteFeature = preferences[PreferencesKeys.favoriteFeatureEnabled]?: false
            UserPreferences(rotateMode,localStore,favoriteFeature)
        }

    private object PreferencesKeys {
        val  isRotateMode = booleanPreferencesKey("isRotationMode")
        val localStoreEnable = booleanPreferencesKey("isEnableLocalStore")
        val favoriteFeatureEnabled = booleanPreferencesKey("isEnableFavoriteFeature")
    }

    suspend fun toggleRotationMode(toggle: Boolean) = dataStore.edit {
        it[PreferencesKeys.isRotateMode] = toggle
    }
    suspend fun toggleLocalStore(toggle: Boolean) = dataStore.edit {
        it[PreferencesKeys.localStoreEnable] = toggle
    }
    suspend fun toggleFavoriteFeature(toggle: Boolean) = dataStore.edit {
        it[PreferencesKeys.favoriteFeatureEnabled] = toggle
    }
}