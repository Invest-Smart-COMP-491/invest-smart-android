package com.comp491.investsmart.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreManager @Inject constructor(
    @ApplicationContext appContext: Context,
) {

    private val dataStore = appContext.dataStore

    val userid: Flow<Int> = dataStore.data.map { preferences ->
        preferences[USERID_KEY] ?: -1
    }

    val email: Flow<String> = dataStore.data.map { preferences ->
        preferences[EMAIL_KEY] ?: ""
    }

    val token: Flow<String> = dataStore.data.map { preferences ->
        preferences[TOKEN_KEY] ?: ""
    }

    val username: Flow<String> = dataStore.data.map { preferences ->
        preferences[USERNAME_KEY] ?: ""
    }

    suspend fun setUserID(userId: Int) {
        dataStore.edit { preferences ->
            preferences[USERID_KEY] = userId
        }
    }

    suspend fun setEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
        }
    }

    suspend fun setToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = "Token $token"
        }
    }

    suspend fun setUsername(username: String) {
        dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = username
        }
    }

    suspend fun deleteUserInfo() {
        dataStore.edit { preferences ->
            preferences.remove(USERID_KEY)
            preferences.remove(EMAIL_KEY)
            preferences.remove(USERNAME_KEY)
            preferences.remove(TOKEN_KEY)
        }
    }

    companion object {
        val Context.dataStore by preferencesDataStore("settings")
        private val USERID_KEY = intPreferencesKey("userid")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val TOKEN_KEY = stringPreferencesKey("token")
    }
}
