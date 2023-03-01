package com.ajcortes.proyectofinalmoviles.repositories

import androidx.datastore.core.DataStore
import com.ajcortes.proyectofinalmoviles.data.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey

class UserPreferencesRepository(
    private val userDataStore: DataStore<Preferences>
){

    fun getUserPreferences(): Flow<UserPreferences>{
        return userDataStore.data.map { userPreferences ->
            val username = userPreferences[stringPreferencesKey(UserPreferences.USER_NAME)]
                ?: UserPreferences.USER_NAME
            val viewPagerVisto = userPreferences[booleanPreferencesKey(UserPreferences.VIEW_PAGER_VISTO.toString())] ?: false

            return@map UserPreferences(
                username = username,
                viewPagerVisto = viewPagerVisto
            )
        }
    }

    suspend fun saveUsername(username : String){
        userDataStore.edit{ userPreferences ->
            userPreferences[stringPreferencesKey(UserPreferences.USER_NAME)] = username
        }
    }

    suspend fun saveViewPagerVisto(viewPagerVisto : Boolean){
        userDataStore.edit { userPreferences ->
            userPreferences[booleanPreferencesKey(UserPreferences.VIEW_PAGER_VISTO.toString())] = viewPagerVisto
        }
    }
}