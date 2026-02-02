package com.lumber.inventory.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
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
        val SETUP_COMPLETED = booleanPreferencesKey("setup_completed")
    }

    companion object {
        const val DEFAULT_SERVER_URL = "http://192.168.1.254"
    }

    /**
     * Flow of the current server URL setting.
     */
    val serverUrlFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.SERVER_URL] ?: DEFAULT_SERVER_URL
        }

    /**
     * Flow of whether initial setup has been completed.
     */
    val setupCompletedFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.SETUP_COMPLETED] ?: false
        }

    /**
     * Get the current server URL synchronously.
     * Note: This should be called from a coroutine context.
     */
    suspend fun getServerUrl(): String {
        return serverUrlFlow.first()
    }

    /**
     * Check if setup has been completed.
     */
    suspend fun isSetupCompleted(): Boolean {
        return setupCompletedFlow.first()
    }

    /**
     * Update the server URL setting.
     */
    suspend fun setServerUrl(url: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.SERVER_URL] = url.trimEnd('/')
        }
    }

    /**
     * Mark setup as completed.
     */
    suspend fun setSetupCompleted(completed: Boolean = true) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.SETUP_COMPLETED] = completed
        }
    }
}
