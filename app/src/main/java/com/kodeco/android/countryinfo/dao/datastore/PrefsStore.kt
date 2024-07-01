package com.kodeco.android.countryinfo.dao.datastore

import kotlinx.coroutines.flow.Flow

interface PrefsStore {
    fun isRotationMode(): Flow<Boolean>
    fun isEnableLocalStore(): Flow<Boolean>
    fun isEnableFavoriteFeature(): Flow<Boolean>
    suspend fun toggleRotationMode()
    suspend fun toggleLocalStore()
    suspend fun toggleFavoriteFeature()
}