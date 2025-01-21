package com.example.core.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDataStore(private val context: Context) {

    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "moviedb.pb")

    suspend fun <T> dataStore(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { pref ->
            pref[key] = value
        }
    }

    suspend fun clear() {
        context.dataStore.edit { pref ->
            pref.clear()
        }
    }

    val isDarkMode: Flow<Boolean> get() = context.dataStore.data.map { pref ->
        pref[IS_DARK_MODE] ?: false
    }

    companion object {
        val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
    }
}