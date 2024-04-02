package com.bbasoglu.swipefun.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val PREFERENCE_NAME = "core_pref"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

suspend fun Context.writeString(key: String, value: String) {
    dataStore.edit { pref -> pref[stringPreferencesKey(key)] = value }
}

fun Context.readString(key: String): Flow<String> {
    return dataStore.data.map { pref ->
        pref[stringPreferencesKey(key)] ?: ""
    }
}

suspend fun Context.writeInt(key: String, value: Int) {
    dataStore.edit { pref -> pref[intPreferencesKey(key)] = value }
}

fun Context.readInt(key: String): Flow<Int> {
    return dataStore.data.map { pref ->
        pref[intPreferencesKey(key)] ?: 0
    }
}

suspend fun Context.writeDouble(key: String, value: Double) {
    dataStore.edit { pref -> pref[doublePreferencesKey(key)] = value }
}

fun Context.readDouble(key: String): Flow<Double> {
    return dataStore.data.map { pref ->
        pref[doublePreferencesKey(key)] ?: 0.0
    }
}

suspend fun Context.writeLong(key: String, value: Long) {
    dataStore.edit { pref -> pref[longPreferencesKey(key)] = value }
}

fun Context.readLong(key: String): Flow<Long> {
    return dataStore.data.map { pref ->
        pref[longPreferencesKey(key)] ?: 0L
    }
}

suspend fun Context.writeBool(key: String, value: Boolean) {
    dataStore.edit { pref -> pref[booleanPreferencesKey(key)] = value }
}

fun Context.readBool(key: String): Flow<Boolean> {
    return dataStore.data.map { pref ->
        pref[booleanPreferencesKey(key)] ?: false
    }
}
