package com.projectfarrel.infokosadmin.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "datauser")
@Suppress("PrivatePropertyName", "unused")
class DataStoreLogin(private val context: Context){

    private val Username = stringPreferencesKey("Username")
    private val PW = stringPreferencesKey("password")


    suspend fun saveData(
        username : String,
        pw:String){

       context.dataStore.edit {

            it[Username] = username
            it[PW] = pw

        }    }
    suspend fun clearData(){
        context.dataStore.edit { it.clear() } }

    val userName: Flow<String> = context.dataStore.data.map { it[Username] ?: ""}
    val userPw: Flow<String> = context.dataStore.data.map { it[PW] ?: "" }
}