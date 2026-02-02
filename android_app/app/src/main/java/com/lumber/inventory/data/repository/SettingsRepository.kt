package com.lumber.inventory.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

/**
 * Repository for managing app settings including server URL.
 */
@Singleton
class SettingsRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private object PreferencesKeys {
        val SERVER_URL = stringPreferencesKey("server_url")
    }
    
    companion object {
        const val DEFAULT_SERVER_URL = "http://raspberrypi.local"
    }
    
    /**
     * Flow of the current server URL setting.
     */
    val serverUrlFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.SERVER_URL] ?: DEFAULT_SERVER_URL
        }
    
    /**
     * Get the current server URL synchronously.
     * Note: This should be called from a coroutine context.
     */
    suspend fun getServerUrl(): String {
        return serverUrlFlow.first()
    }
    
    /**
     * Update the server URL setting.
     */
    suspend fun setServerUrl(url: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.SERVER_URL] = url.trimEnd('/')
        }
    }
}
